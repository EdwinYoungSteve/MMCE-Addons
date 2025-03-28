package github.alecsio.mmceaddons.common.tile.machinecomponent.ae2;

import appeng.api.AEApi;
import appeng.api.config.Actionable;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.networking.ticking.IGridTickable;
import appeng.api.storage.IMEInventory;
import appeng.api.util.AEPartLocation;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.tile.handler.IEssentiaHandler;
import github.kasuminova.mmce.common.tile.base.MEMachineComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import thaumicenergistics.api.storage.IAEEssentiaStack;
import thaumicenergistics.api.storage.IEssentiaStorageChannel;

public abstract class MEEssentiaBus extends MEMachineComponent implements IGridTickable, IEssentiaHandler {

    protected IMEInventory<IAEEssentiaStack> getStorageInventory() {
        IStorageGrid storage = this.getGridNode(AEPartLocation.UP).getGrid().getCache(IStorageGrid.class);
        return storage.getInventory(getChannel());
    }

    protected IEssentiaStorageChannel getChannel() {
        return AEApi.instance().storage().getStorageChannel(IEssentiaStorageChannel.class);
    }

    protected abstract boolean canPerformOperation(Actionable actionable, RequirementEssentia essentia);

    @Override
    public CraftCheck canHandle(RequirementEssentia essentia) {
        return canPerformOperation(Actionable.SIMULATE, essentia) ? CraftCheck.success() : CraftCheck.failure(":(");
    }

    @Override
    public boolean handle(RequirementEssentia essentia) {
        return canPerformOperation(Actionable.MODULATE, essentia);
    }
}
