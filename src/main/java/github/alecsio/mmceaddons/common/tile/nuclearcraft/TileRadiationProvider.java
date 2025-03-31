package github.alecsio.mmceaddons.common.tile.nuclearcraft;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.handler.strategy.RandomChunkSelectionStrategy;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentRadiationProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import nc.capability.radiation.source.IRadiationSource;
import nc.radiation.RadiationHelper;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class TileRadiationProvider extends AbstractMultiChunkHandler implements MachineComponentTile {

    public TileRadiationProvider() {
        super(new RandomChunkSelectionStrategy());
    }

    @Override
    protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos) {
        return getSourceFrom(randomBlockPos).getRadiationBuffer();
    }

    public IRadiationSource getSourceFrom(BlockPos pos) {
        return RadiationHelper.getRadiationSource(this.world.getChunk(pos));
    }

    public static class Input extends TileRadiationProvider {
        @Nullable
        @Override
        public MachineComponentRadiationProvider provideComponent() {
            return new MachineComponentRadiationProvider(IOType.INPUT, this);
        }

        @Override
        public boolean canHandle(IMultiChunkRequirement requirement, BlockPos controllerPos) {
            return true;
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return true;
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
            return Math.min(amountToHandle, amountInChunk);
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
            IRadiationSource radiationSource = getSourceFrom(controllerPos);
            radiationSource.setRadiationLevel(0);
            radiationSource.setRadiationBuffer(0);
        }
    }

    public static class Output extends TileRadiationProvider {
        @Nullable
        @Override
        public MachineComponentRadiationProvider provideComponent() {
            return new MachineComponentRadiationProvider(IOType.OUTPUT, this);
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount + amountToModify <= requirement.getMaxPerChunk();
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
            return Math.max(0, Math.min(amountToHandle, requirement.getMaxPerChunk() - amountInChunk));
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
            RadiationHelper.addToSourceBuffer(getSourceFrom(controllerPos), amountToHandle);
        }
    }
}
