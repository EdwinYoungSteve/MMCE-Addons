package github.alecsio.mmceaddons.common.assembly;

import appeng.api.AEApi;
import appeng.api.IAppEngApi;
import appeng.api.config.Actionable;
import appeng.api.features.ILocatable;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.api.networking.energy.IEnergyGrid;
import appeng.api.networking.security.IActionHost;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.IStorageHelper;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.me.helpers.PlayerSource;
import appeng.util.item.AEItemStack;
import github.alecsio.mmceaddons.common.LoadedModsCache;
import github.alecsio.mmceaddons.common.config.MMCEAConfig;
import github.alecsio.mmceaddons.common.item.ItemAdvancedMachineDisassembler;
import github.alecsio.mmceaddons.util.NBTUtil;
import ink.ikx.mmce.common.utils.StructureIngredient;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stanhebben.zenscript.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class AdvancedMachineDisassembly extends AbstractMachineAssembly {

    private static final Logger LOGGER = LogManager.getLogger();

    public AdvancedMachineDisassembly(World world, BlockPos controllerPos, EntityPlayer player, StructureIngredient ingredient) {
        super(world, controllerPos, player, ingredient);
    }

    @Override
    public void assembleTick() {
        List<StructureIngredient.ItemIngredient> itemIngredients = ingredient.itemIngredient();
        Iterator<StructureIngredient.ItemIngredient> iterator = itemIngredients.iterator();

        for (int i = 0; i < MMCEAConfig.disassemblyBlocksProcessedPerTick; i++) {
            if (!iterator.hasNext()) {
                completed = true;
                break;
            }
            StructureIngredient.ItemIngredient ingredientToProcess = iterator.next();
            tryBreakBlock(ingredientToProcess);
            iterator.remove();
        }

        sendAndResetError();
    }

    private void tryBreakBlock(StructureIngredient.ItemIngredient ingredientToProcess) {
        BlockPos toBreakPos = getControllerPos().add(ingredientToProcess.pos());

        if (!canBreakBlockAt(toBreakPos)) {return;}

        // The ingredient list contains a list of all valid blocks for the given block pos. For example, the different tiers
        // of hatches

        ItemStack stack = ingredientToProcess.ingredientList().get(0).getFirst();

        Block blockToBreak = world.getBlockState(toBreakPos).getBlock();
        TileEntity tileEntity = world.getTileEntity(toBreakPos);
        if (blockToBreak == Blocks.AIR || isItemHandler(tileEntity) || isFluidHandler(tileEntity)) {
            return;
        }

        boolean handled = false;
        ItemStack disassembler = null;
        // player inv
        for (final ItemStack stackInSlot : player.inventory.mainInventory) {
            // If there's more than one assembler this will work incorrectly (say if one is linked to the ME system and the other is not)
            // but idk if it's worth addressing
            if (stackInSlot.getItem() instanceof ItemAdvancedMachineDisassembler) {
                disassembler = stackInSlot;
            }
        }

        if (disassembler == null) {
            lastError = new TextComponentTranslation("error.assembler.not.found");
            return;
        }

        int modeIndex = NBTUtil.getOrDefault(disassembler, ItemAdvancedMachineDisassembler.MODE_INDEX, 0);
        AssemblyModes mode = AssemblyModes.getSupportedModes().get(modeIndex);

        if (LoadedModsCache.aeLoaded && mode.supports(AssemblySupportedMods.APPLIEDENERGISTICS2)) {
            handled = canMEHandle(stack, disassembler);
        }

        if (!handled && LoadedModsCache.projecteLoaded && mode.supports(AssemblySupportedMods.PROJECTE)) {
            handled = canEMCHandle(stack);
        }

        if (!handled) {
            handled = player.inventory.addItemStackToInventory(stack);
        }

        if (!handled) {
            unhandledBlocks.add(stack.getDisplayName());
            return;
        }

        lastError = null; // If it was handled, we don't care
        IBlockState state = world.getBlockState(toBreakPos);
        world.setBlockToAir(toBreakPos);
        world.getBlockState(toBreakPos).getBlock().neighborChanged(state, world, toBreakPos, world.getBlockState(toBreakPos.up()).getBlock(), toBreakPos.up());
        world.playSound(null, toBreakPos, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Optional.Method(modid = LoadedModsCache.AE2)
    private boolean canMEHandle(ItemStack stack, @NotNull ItemStack assembler) {
        IAppEngApi aeApi = AEApi.instance();
        java.util.Optional<Long> optEncryptionKey = getOptionalEncryptionKeyFrom(assembler);
        if (!optEncryptionKey.isPresent()) {
            return false;
        }

        long parsedKey = optEncryptionKey.get();
        ILocatable securityStation = aeApi.registries().locatable().getLocatableBy(parsedKey);
        if (!(securityStation instanceof IActionHost host)) {
            lastError = new TextComponentTranslation("error.security.station.not.found");
            return false;
        }

        IGridNode node = host.getActionableNode();
        IGrid targetGrid = node.getGrid();
        IEnergyGrid energyGrid = targetGrid.getCache(IEnergyGrid.class);
        IStorageGrid storageGrid = targetGrid.getCache(IStorageGrid.class);

        IStorageHelper storageHelper = aeApi.storage();

        IMEMonitor<IAEItemStack> itemStorage = storageGrid.getInventory(storageHelper.getStorageChannel(IItemStorageChannel.class));

        IAEStack<IAEItemStack> aeStack = aeApi.storage().poweredInsert(energyGrid, itemStorage, AEItemStack.fromItemStack(stack), new PlayerSource(player, host), Actionable.SIMULATE);
        if (aeStack == null) { // Was able to insert all of it
            storageHelper.poweredInsert(energyGrid, itemStorage, AEItemStack.fromItemStack(stack), new PlayerSource(player, host), Actionable.MODULATE);
            return true;
        }

        return false;
    }

    @Optional.Method(modid = LoadedModsCache.PROJECTE)
    private boolean canEMCHandle(ItemStack stack) {
        if (player == null) {
            LOGGER.error("Null player in assembly. This should probably not happen.");
            return false;
        }

        IKnowledgeProvider provider = player.getCapability(ProjectEAPI.KNOWLEDGE_CAPABILITY, null);
        if (provider == null) {
            LOGGER.error("Null IKnowledgeProvider in assembly. This should probably not happen.");
            return false;
        }

        boolean hasEmc = EMCHelper.doesItemHaveEmc(stack);
        if (!hasEmc) {
            return false;
        }

        long emc = provider.getEmc();
        long stackEmcValue = EMCHelper.getEmcValue(stack);

        if (!provider.hasKnowledge(stack)) {
            lastError = new TextComponentTranslation("message.assembly.missing.item.knowledge", stack.getDisplayName());
            return false;
        }

        if (stackEmcValue > 0 && emc > Long.MAX_VALUE - stackEmcValue) { // would overflow
            lastError = new TextComponentTranslation("message.assembly.overflow.emc", stack.getDisplayName(), stack.getCount(), stackEmcValue, emc);
            return false;
        }

        provider.setEmc(emc + stackEmcValue);
        return true;
    }

    private boolean canBreakBlockAt(BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return player.isAllowEdit() && world.isBlockModifiable(player, pos) && !state.getBlock().isAir(state, world, pos);
    }

    private boolean isFluidHandler(TileEntity te) {
        if (te == null) {return false;}
        boolean fluidHandler = false;
        for (EnumFacing facing : EnumFacing.values()) {
            fluidHandler = te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing);
            if (fluidHandler) {
                break;
            }
        }
        return fluidHandler || te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
    }

    private boolean isItemHandler(TileEntity te) {
        if (te == null) {return false;}
        boolean itemHandler = false;
        for (EnumFacing facing : EnumFacing.values()) {
            itemHandler = te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
            if (itemHandler) {
                break;
            }
        }
        return itemHandler || te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
    }

    private void sendAndResetError() {
        if (lastError != null) {
            player.sendMessage(lastError);
            lastError = null;
        }
    }
}
