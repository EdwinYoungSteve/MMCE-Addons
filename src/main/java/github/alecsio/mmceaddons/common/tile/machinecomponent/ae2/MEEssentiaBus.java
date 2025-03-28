package github.alecsio.mmceaddons.common.tile.machinecomponent.ae2;

import appeng.api.AEApi;
import appeng.api.config.Actionable;
import appeng.api.networking.IGridNode;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.networking.ticking.IGridTickable;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.api.networking.ticking.TickingRequest;
import appeng.api.storage.IMEInventory;
import appeng.api.util.AEPartLocation;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.kasuminova.mmce.common.tile.base.MEMachineComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import thaumicenergistics.api.EssentiaStack;
import thaumicenergistics.api.storage.IAEEssentiaStack;
import thaumicenergistics.api.storage.IEssentiaStorageChannel;

import javax.annotation.Nonnull;

public abstract class MEEssentiaBus extends MEMachineComponent implements IGridTickable, IRequirementHandler<EssentiaStack> {

    protected IMEInventory<IAEEssentiaStack> getStorageInventory() {
        IStorageGrid storage = this.getGridNode(AEPartLocation.UP).getGrid().getCache(IStorageGrid.class);
        return storage.getInventory(getChannel());
    }

    protected IEssentiaStorageChannel getChannel() {
        return AEApi.instance().storage().getStorageChannel(IEssentiaStorageChannel.class);
    }

    protected abstract boolean canPerformOperation(Actionable actionable, EssentiaStack essentia);

    @Nonnull
    @Override
    public TickingRequest getTickingRequest(@Nonnull IGridNode iGridNode) {
        return new TickingRequest(5, 40, false, true) {}; // Magic numbers
    }

    @Nonnull
    @Override
    public TickRateModulation tickingRequest(@Nonnull IGridNode iGridNode, int i) {
        return TickRateModulation.SLOWER;
    }

    @Override
    public CraftCheck canHandle(EssentiaStack essentia) {
        return canPerformOperation(Actionable.SIMULATE, essentia) ? CraftCheck.success() : CraftCheck.failure(":(");
    }

    @Override
    public void handle(EssentiaStack essentia) {
        canPerformOperation(Actionable.MODULATE, essentia);
    }
}
