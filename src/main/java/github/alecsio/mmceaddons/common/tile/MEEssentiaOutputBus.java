package github.alecsio.mmceaddons.common.tile;

import appeng.api.config.Actionable;
import appeng.api.networking.IGridNode;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.api.networking.ticking.TickingRequest;
import appeng.api.storage.IMEMonitor;
import appeng.api.util.AEPartLocation;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.lib.ModularMachineryAddonsBlocks;
import github.alecsio.mmceaddons.common.tile.handler.IEssentiaHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentEssentiaProvider;
import github.alecsio.mmceaddons.common.tile.machinecomponent.ae2.MEEssentiaBus;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.api.EssentiaStack;
import thaumicenergistics.api.storage.IAEEssentiaStack;
import thaumicenergistics.integration.appeng.AEEssentiaStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class MEEssentiaOutputBus extends MEEssentiaBus {

    @Override
    public ItemStack getVisualItemStack() {
        return new ItemStack(ModularMachineryAddonsBlocks.blockMEEssentiaOutputBus);
    }

    @Nullable
    @Override
    public MachineComponent<IEssentiaHandler> provideComponent() {
        return new MachineComponentEssentiaProvider(IOType.OUTPUT, this);
    }

    @Nonnull
    @Override
    public TickingRequest getTickingRequest(@Nonnull IGridNode iGridNode) {
        return new TickingRequest(5, 40, false, true) {};
    }

    @Nonnull
    @Override
    public TickRateModulation tickingRequest(@Nonnull IGridNode iGridNode, int i) {
        return TickRateModulation.FASTER;
    }

    @Override
    protected boolean canHandleAmount(EssentiaStack essentia, int currentAmount) {
        IStorageGrid storageGrid = this.getGridNode(AEPartLocation.UP).getGrid().getCache(IStorageGrid.class);
        IMEMonitor<IAEEssentiaStack> storage = storageGrid.getInventory(this.getChannel());

        return storage.canAccept(AEEssentiaStack.fromEssentiaStack(essentia));
    }

    @Override
    public boolean handle(RequirementEssentia essentia) {
        boolean allHandled = true;
        IStorageGrid storageGrid = this.getGridNode(AEPartLocation.UP).getGrid().getCache(IStorageGrid.class);
        IMEMonitor<IAEEssentiaStack> storage = storageGrid.getInventory(this.getChannel());

        for (Map.Entry<Aspect, Integer> essentiaEntry : essentia.getEssentiaList().aspects.entrySet()) {
            AEEssentiaStack essentiaToInsert = AEEssentiaStack.fromEssentiaStack(new EssentiaStack(essentiaEntry.getKey(), essentiaEntry.getValue()));
            if (!storage.canAccept(essentiaToInsert)) {
                allHandled = false;
                break;
            }
            IAEEssentiaStack notInserted = storage.injectItems(essentiaToInsert, Actionable.SIMULATE, this.source);
            if (notInserted != null && notInserted.getStackSize() > 0) {
                allHandled = false;
                break;
            }
            storage.injectItems(essentiaToInsert, Actionable.MODULATE, this.source);
        }

        return allHandled;
    }
}
