package github.alecsio.mmceaddons.common.tile;

import appeng.api.config.Actionable;
import appeng.api.networking.IGridNode;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.api.networking.ticking.TickingRequest;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.lib.ModularMachineryAddonsBlocks;
import github.alecsio.mmceaddons.common.tile.handler.IEssentiaHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentEssentiaProvider;
import github.alecsio.mmceaddons.common.tile.machinecomponent.ae2.MEEssentiaBus;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import net.minecraft.item.ItemStack;
import thaumicenergistics.integration.appeng.AEEssentiaStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    protected boolean canPerformOperation(Actionable actionable, RequirementEssentia essentia) {
        return getStorageInventory().injectItems(AEEssentiaStack.fromEssentiaStack(essentia.getEssentiaStack()), actionable, this.source) == null;
    }
}
