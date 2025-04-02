package github.alecsio.mmceaddons.common.tile.thaumcraft;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementVis;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.handler.strategy.RandomChunkSelectionStrategy;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentVisProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.util.math.BlockPos;
import thaumcraft.api.aura.AuraHelper;

import javax.annotation.Nullable;

public abstract class TileVisProvider extends AbstractMultiChunkHandler<RequirementVis> implements MachineComponentTile {

    protected TileVisProvider() {
        super(new RandomChunkSelectionStrategy());
    }

    public static class Input extends TileVisProvider {
        @Override
        protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos) {
            return AuraHelper.getVis(this.world, randomBlockPos);
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
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
            AuraHelper.drainVis(this.world, controllerPos, (float) amountToHandle, false);
        }

        @Nullable
        @Override
        public MachineComponentVisProvider provideComponent() {
            return new MachineComponentVisProvider(IOType.INPUT, this);
        }
    }

    public static class Output extends TileVisProvider {

        @Override
        protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos) {
            return AuraHelper.getVis(this.world, randomBlockPos);
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
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
            AuraHelper.addVis(this.world, controllerPos, (float) amountToHandle);
        }

        @Nullable
        @Override
        public MachineComponentVisProvider provideComponent() {
            return new MachineComponentVisProvider(IOType.OUTPUT, this);
        }
    }

}
