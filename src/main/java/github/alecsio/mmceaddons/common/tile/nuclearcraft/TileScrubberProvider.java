package github.alecsio.mmceaddons.common.tile.nuclearcraft;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.handler.strategy.SequentialChunkSelectionStrategy;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentScrubberProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import nc.capability.radiation.source.IRadiationSource;
import nc.radiation.RadiationHelper;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TileScrubberProvider extends AbstractMultiChunkHandler implements MachineComponentTile {

    protected TileScrubberProvider() {
        super(new SequentialChunkSelectionStrategy());
    }

    public IRadiationSource getSourceFrom(BlockPos pos) {
        return RadiationHelper.getRadiationSource(this.world.getChunk(pos));
    }

    @Override
    protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos) {
        // In this case it doesn't really matter if we use level or buffer because we're setting both to 0 anyways.
        return getSourceFrom(randomBlockPos).getRadiationLevel();
    }

    @Override
    protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
        return true;
    }

    @Override
    protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
        return amountInChunk;
    }

    @Override
    protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
        IRadiationSource radiationSource = getSourceFrom(controllerPos);
        radiationSource.setRadiationLevel(0);
        radiationSource.setRadiationBuffer(0);
    }

    @Nullable
    @Override
    public MachineComponentScrubberProvider provideComponent() {
        return new MachineComponentScrubberProvider(IOType.INPUT, this);
    }
}
