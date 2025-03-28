package github.alecsio.mmceaddons.common.tile.machinecomponent.ae2;

import appeng.api.AEApi;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.networking.ticking.IGridTickable;
import appeng.api.storage.data.IItemList;
import appeng.api.util.AEPartLocation;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.tile.handler.IEssentiaHandler;
import github.kasuminova.mmce.common.tile.base.MEMachineComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.api.EssentiaStack;
import thaumicenergistics.api.storage.IAEEssentiaStack;
import thaumicenergistics.api.storage.IEssentiaStorageChannel;

import java.util.Map;

public abstract class MEEssentiaBus extends MEMachineComponent implements IGridTickable, IEssentiaHandler {

    @Override
    public CraftCheck canHandle(RequirementEssentia essentia) {
        IItemList<IAEEssentiaStack> storedAspects = getStoredAspects();
        for (Map.Entry<Aspect, Integer> essentiaEntry : essentia.getEssentiaList().aspects.entrySet()) {
            boolean matches = false;
            for (IAEEssentiaStack stack : storedAspects) {
                if (essentiaEntry.getKey().getTag().equalsIgnoreCase(stack.getAspect().getName()) && canHandleAmount(new EssentiaStack(essentiaEntry.getKey(), essentiaEntry.getValue()), stack.getStack().getAmount())) {
                    matches = true;
                    break;
                }
            }
            if (!matches) {
                return CraftCheck.failure(":(");
            }
        }
        return CraftCheck.success();
    }

    protected abstract boolean canHandleAmount(EssentiaStack essentia, int currentAmount);

    public IItemList<IAEEssentiaStack> getStoredAspects() {
        IEssentiaStorageChannel channel = this.getChannel();
        IItemList<IAEEssentiaStack> list;

        IStorageGrid storage = this.getGridNode(AEPartLocation.UP).getGrid().getCache(IStorageGrid.class);
        list = storage.getInventory(channel).getStorageList();

        return list;
    }

    protected IEssentiaStorageChannel getChannel() {
        return AEApi.instance().storage().getStorageChannel(IEssentiaStorageChannel.class);
    }
}
