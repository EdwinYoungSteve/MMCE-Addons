package github.alecsio.mmceaddons.common.tile.nuclearcraft;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.RequirementScrubber;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentScrubberProvider;
import github.alecsio.mmceaddons.common.tile.wrapper.RadiationHelperWrapper;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TileScrubberProvider extends AbstractMultiChunkHandler<RequirementScrubber> implements MachineComponentTile {

    @Override
    protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos) {
        return RadiationHelperWrapper.getRadiationAmount(this.world.getChunk(randomBlockPos));
    }

    @Override
    protected boolean canChunkHandle(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
        return true;
    }

    @Override
    protected void handleAmount(IMultiChunkRequirement requirement, BlockPos blockPosInChunk, double amountToHandle) {
        RadiationHelperWrapper.scrubRadiation(world.getChunk(blockPosInChunk));
    }

    @Nullable
    @Override
    public MachineComponentScrubberProvider provideComponent() {
        return new MachineComponentScrubberProvider(IOType.INPUT, this);
    }
}
