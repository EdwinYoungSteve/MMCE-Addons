package github.alecsio.mmceaddons.common.item;

import github.alecsio.mmceaddons.common.assembly.IMachineAssembly;
import github.alecsio.mmceaddons.common.assembly.MachineAssemblyManager;
import github.kasuminova.mmce.common.util.DynamicPattern;
import hellfirepvp.modularmachinery.common.block.BlockController;
import hellfirepvp.modularmachinery.common.block.BlockFactoryController;
import hellfirepvp.modularmachinery.common.machine.DynamicMachine;
import hellfirepvp.modularmachinery.common.tiles.base.TileMultiblockMachineController;
import hellfirepvp.modularmachinery.common.util.BlockArray;
import hellfirepvp.modularmachinery.common.util.BlockArrayCache;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

import static github.alecsio.mmceaddons.common.assembly.handler.MachineAssemblyEventHandler.ASSEMBLY_ACCESS_TOKEN;

public class BuilderRightClickHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Item item = event.getItemStack().getItem();
        World world = event.getWorld();
        if (item == Items.AIR || !(item instanceof BaseItemAdvancedMachineBuilder builder) || world.isRemote) {
            return;
        }
        EntityPlayer player = event.getEntityPlayer();
        BlockPos pos = event.getPos();

        Boolean access = ASSEMBLY_ACCESS_TOKEN.getIfPresent(player);

        if (Boolean.FALSE.equals(access)) {
            player.sendMessage(new TextComponentTranslation("message.assembly.too.quickly"));
            return;
        }
        ASSEMBLY_ACCESS_TOKEN.put(player, false);

        TileEntity tileEntity = world.getTileEntity(pos);

        if (!(tileEntity instanceof TileMultiblockMachineController controller)) {
            return;
        }

        Block block = world.getBlockState(pos).getBlock();
        DynamicMachine machine = controller.getBlueprintMachine();
        if (machine == null) {
            if (block instanceof BlockController) {
                machine = ((BlockController) block).getParentMachine();
            }
            if (block instanceof BlockFactoryController) {
                machine = ((BlockFactoryController) block).getParentMachine();
            }
        }

        if (machine == null) {
            player.sendMessage(new TextComponentTranslation("message.assembly.missing.machine"));
            return;
        }

        if (MachineAssemblyManager.machineExists(pos)) {
            player.sendMessage(new TextComponentTranslation("message.assembly.tip.already_assembly"));
            return;
        }

        event.setCanceled(true);
        event.setCancellationResult(EnumActionResult.SUCCESS);

        EnumFacing controllerFacing = player.world.getBlockState(pos).getValue(BlockController.FACING);
        BlockArray machinePattern = new BlockArray(BlockArrayCache.getBlockArrayCache(machine.getPattern(), controllerFacing));

        int dynamicPatternSize = 0;
        Map<String, DynamicPattern> dynamicPatterns = machine.getDynamicPatterns();
        for (final DynamicPattern pattern : dynamicPatterns.values()) {
            dynamicPatternSize = Math.max(dynamicPatternSize, pattern.getMinSize());
        }

        for (final DynamicPattern pattern : dynamicPatterns.values()) {
            pattern.addPatternToBlockArray(
                    machinePattern,
                    Math.min(Math.max(pattern.getMinSize(), dynamicPatternSize), pattern.getMaxSize()),
                    pattern.getFaces().iterator().next(),
                    controllerFacing);
        }

        IMachineAssembly machineAssembly = builder.getAssembly(world, pos, player, machinePattern);

        MachineAssemblyManager.addMachineAssembly(machineAssembly);
    }
}
