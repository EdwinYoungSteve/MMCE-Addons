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
import thaumicenergistics.util.AEUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class MEEssentiaInputBus extends MEEssentiaBus {

    public MEEssentiaInputBus() {
    }

    @Override
    public ItemStack getVisualItemStack() {
        return new ItemStack(ModularMachineryAddonsBlocks.blockMEEssentiaInputBus);
    }

    @Nullable
    @Override
    public MachineComponent<IEssentiaHandler> provideComponent() {
        return new MachineComponentEssentiaProvider(IOType.INPUT, this);
    }

    @Nonnull
    @Override
    public TickingRequest getTickingRequest(@Nonnull IGridNode iGridNode) {
        return new TickingRequest(5, 40, false, true) {};
    }

    @Nonnull
    @Override
    public TickRateModulation tickingRequest(@Nonnull IGridNode iGridNode, int i) {
        // getStoredAspects().forEach(aspect -> System.out.println("Aspect: " + aspect.getAspect().getName() + " :" + aspect.getStackSize()));
        return TickRateModulation.SLOWER;
    }

    @Override
    protected boolean canHandleAmount(EssentiaStack essentiaStack, int currentAmount) {
        return essentiaStack.getAmount() <= currentAmount;
    }

    @Override
    public boolean handle(RequirementEssentia essentia) {
        boolean allHandled = true;
        IStorageGrid storage = this.getGridNode(AEPartLocation.UP).getGrid().getCache(IStorageGrid.class);
        IMEMonitor<IAEEssentiaStack> monitor = storage.getInventory(super.getChannel());

        for (Map.Entry<Aspect, Integer> essentiaEntry : essentia.getEssentiaList().aspects.entrySet()) {
            IAEEssentiaStack canExtract = monitor.extractItems(AEUtil.getAEStackFromAspect(essentiaEntry.getKey(), essentiaEntry.getValue()), Actionable.SIMULATE, this.source);
            if (canExtract == null || canExtract.getStackSize() != essentiaEntry.getValue()) {
                allHandled = false;
                break;
            }
            monitor.extractItems(canExtract, Actionable.MODULATE, this.source);
        }
        this.markDirty();
        return allHandled;
    }
}
