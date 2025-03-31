package github.alecsio.mmceaddons.common.tile.bloodmagic;

import WayofTime.bloodmagic.demonAura.WorldDemonWillHandler;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementWillMultiChunk;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.handler.strategy.RandomChunkSelectionStrategy;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentWillMultiChunkProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class TileWillMultiChunkProvider extends AbstractMultiChunkHandler implements MachineComponentTile {

    public TileWillMultiChunkProvider() {
        super(new RandomChunkSelectionStrategy());
    }

    @Override
    protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos) {
        return WorldDemonWillHandler.getCurrentWill(this.world, randomBlockPos, ((RequirementWillMultiChunk)requirement).willType);
    }

    public static class Input extends TileWillMultiChunkProvider {
        @Nullable
        @Override
        public MachineComponentWillMultiChunkProvider provideComponent() {
            return new MachineComponentWillMultiChunkProvider(IOType.INPUT, this) {};
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
            return Math.min(amountToHandle, amountInChunk);
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
            WorldDemonWillHandler.drainWill(this.world, controllerPos, ((RequirementWillMultiChunk)requirement).willType, amountToHandle, true);
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount - amountToModify < requirement.getMinPerChunk();
        }
    }

    public static class Output extends TileWillMultiChunkProvider {
        @Nullable
        @Override
        public MachineComponentWillMultiChunkProvider provideComponent() {
            return new MachineComponentWillMultiChunkProvider(IOType.OUTPUT, this) {};
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement) {
            return Math.max(0, Math.min(amountToHandle, requirement.getMaxPerChunk() - amountInChunk));
        }

        @Override
        protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle) {
            WorldDemonWillHandler.fillWill(this.world, controllerPos, ((RequirementWillMultiChunk)requirement).willType, amountToHandle, true);
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement) {
            return currentAmount + amountToModify <= requirement.getMaxPerChunk();
        }
    }
}
