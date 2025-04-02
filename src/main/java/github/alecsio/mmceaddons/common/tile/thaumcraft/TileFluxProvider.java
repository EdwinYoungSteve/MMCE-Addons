package github.alecsio.mmceaddons.common.tile.thaumcraft;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementFlux;
import github.alecsio.mmceaddons.common.exception.ConsistencyException;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.handler.strategy.RandomChunkSelectionStrategy;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentFluxProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.util.math.BlockPos;
import thaumcraft.api.aura.AuraHelper;

import javax.annotation.Nullable;

public abstract class TileFluxProvider extends AbstractMultiChunkHandler<RequirementFlux> implements MachineComponentTile {

    public TileFluxProvider() {
        super(new RandomChunkSelectionStrategy());
    }

    @Override
    protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos blockPosInChunk) {
        return AuraHelper.getFlux(this.world, blockPosInChunk);
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
        protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount - amountToModify >= 0;
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
            return Math.min(amountToHandle, amountInChunk);
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos blockPosInChunk, double amountToHandle) {
            AuraHelper.drainFlux(this.world, blockPosInChunk, castWithSafeguard(amountToHandle), false);
        }
    }

    public static class Output extends TileFluxProvider {
        @Nullable
        @Override
        public MachineComponentFluxProvider provideComponent() {
            return new MachineComponentFluxProvider(this, IOType.OUTPUT);
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount + amountToModify <= Float.MAX_VALUE;
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
            return amountToHandle;
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos blockPosInChunk, double amountToHandle) {
            AuraHelper.polluteAura(this.world, blockPosInChunk, castWithSafeguard(amountToHandle), false);
        }
    }
}
