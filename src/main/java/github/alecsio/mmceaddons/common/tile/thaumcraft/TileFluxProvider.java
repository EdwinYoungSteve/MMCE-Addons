package github.alecsio.mmceaddons.common.tile.thaumcraft;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementFlux;
import github.alecsio.mmceaddons.common.exception.ConsistencyException;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentFluxProvider;
import github.alecsio.mmceaddons.common.tile.wrapper.AuraHelperWrapper;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class TileFluxProvider extends AbstractMultiChunkHandler<RequirementFlux> implements MachineComponentTile {

    @Override
    protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos blockPosInChunk) {
        return AuraHelperWrapper.Flux.getFlux(this.world, blockPosInChunk);
    }

    protected float castWithSafeguard(double amount) {
        if (amount > Float.MAX_VALUE) {
            throw new ConsistencyException("This should never happen");
        }
        return (float) amount;
    }

    public static class Input extends TileFluxProvider {
        @Nullable
        @Override
        public MachineComponent<IRequirementHandler<RequirementFlux>> provideComponent() {
            return new MachineComponentFluxProvider(this, IOType.INPUT);
        }

        @Override
        protected boolean canChunkHandle(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount - amountToModify >= 0;
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos blockPosInChunk, double amountToHandle) {
            AuraHelperWrapper.Flux.drainFlux(this.world, pos, castWithSafeguard(amountToHandle));
        }
    }

    public static class Output extends TileFluxProvider {
        @Nullable
        @Override
        public MachineComponentFluxProvider provideComponent() {
            return new MachineComponentFluxProvider(this, IOType.OUTPUT);
        }

        @Override
        protected boolean canChunkHandle(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount + amountToModify <= Float.MAX_VALUE;
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos blockPosInChunk, double amountToHandle) {
            AuraHelperWrapper.Flux.addFlux(this.world, pos, castWithSafeguard(amountToHandle));
        }
    }
}
