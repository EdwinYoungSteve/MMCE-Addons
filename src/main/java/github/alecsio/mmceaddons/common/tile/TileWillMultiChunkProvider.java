package github.alecsio.mmceaddons.common.tile;

import WayofTime.bloodmagic.demonAura.WorldDemonWillHandler;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import github.alecsio.mmceaddons.common.chunks.ChunksReader;
import github.alecsio.mmceaddons.common.crafting.requirement.AbstractMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementWillMultiChunk;
import github.alecsio.mmceaddons.common.crafting.requirement.types.RequirementTypeWillMultiChunk;
import github.alecsio.mmceaddons.common.tile.handler.AbstractMultiChunkHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentWillMultiChunkProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import kport.modularmagic.common.integration.jei.ingredient.DemonWill;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;
import java.util.stream.Collectors;

public abstract class TileWillMultiChunkProvider extends AbstractMultiChunkHandler<AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk>> implements MachineComponentTile {

    private final ChunksReader chunksReader = ChunksReader.getInstance();


    public double getWill(EnumDemonWillType willType, int chunkRange, BlockPos controllerPos) {
        return chunksReader.getSurroundingChunks(this.world, controllerPos, chunkRange).stream()
                .filter(Chunk::isLoaded)
                .collect(Collectors.summarizingDouble(chunk -> WorldDemonWillHandler.getCurrentWill(this.world, getBlockInChunk(chunk), willType)))
                .getSum();
    }

    @Override
    protected double getAmountInChunk(AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement, BlockPos randomBlockPos) {
        return WorldDemonWillHandler.getCurrentWill(this.world, randomBlockPos, ((RequirementWillMultiChunk)requirement).willType);
    }

    public static class Input extends TileWillMultiChunkProvider {
        @Nullable
        @Override
        public MachineComponentWillMultiChunkProvider provideComponent() {
            return new MachineComponentWillMultiChunkProvider(IOType.INPUT, this) {};
        }

        @Override
        protected double getAmountToApply(double amountInChunk, double amountToHandle, AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement) {
            return Math.min(amountToHandle, amountInChunk);
        }

        @Override
        protected void handleAmount(AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement, BlockPos controllerPos, double amountToHandle) {
            WorldDemonWillHandler.drainWill(this.world, controllerPos, ((RequirementWillMultiChunk)requirement).willType, amountToHandle, true);
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement) {
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
        protected double getAmountToApply(double amountInChunk, double amountToHandle, AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement) {
            return Math.max(0, Math.min(amountToHandle, requirement.getMaxPerChunk() - amountInChunk));
        }

        @Override
        protected void handleAmount(AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement, BlockPos controllerPos, double amountToHandle) {
            WorldDemonWillHandler.fillWill(this.world, controllerPos, ((RequirementWillMultiChunk)requirement).willType, amountToHandle, true);
        }

        @Override
        protected boolean isValidChunk(double currentAmount, double amountToModify, AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> requirement) {
            return currentAmount + amountToModify >= requirement.getMaxPerChunk();
        }
    }
}
