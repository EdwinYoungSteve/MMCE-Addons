package github.alecsio.mmceaddons.common.tile.handler;

import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.tile.handler.chunks.ChunksReader;
import github.alecsio.mmceaddons.common.tile.handler.strategy.ChunkSelectionStrategy;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.List;

public abstract class AbstractMultiChunkHandler<T extends IMultiChunkRequirement> extends TileColorableMachineComponent implements IRequirementHandler<T> {
    private final ChunksReader chunksReader = ChunksReader.getInstance();
    private final ChunkSelectionStrategy strategy;

    protected AbstractMultiChunkHandler(ChunkSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public CraftCheck canHandle(T requirement) {
        return handle(requirement, false) == 0 ? CraftCheck.success() : CraftCheck.failure("Cannot handle this requirement");
    }

    @Override
    public void handle(T requirement) {
        handle(requirement, true);
    }

    public double handle(T requirement, boolean doAction) {
        List<Chunk> chunks = chunksReader.getSurroundingChunks(world, this.pos, requirement.getChunkRange());
        if (chunks.isEmpty()) return requirement.getAmount(); // No chunks, return full amount

        double amountToHandle = requirement.getAmount();
        int maxAttempts = chunks.size() * 2; // Prevent infinite loops

        for (int attempts = 0; amountToHandle > 0 && attempts < maxAttempts; attempts++) {
            Chunk chunk = strategy.selectChunk(chunks);
            if (chunk == null || !chunk.isLoaded()) continue; // Skip unloaded chunks

            BlockPos pos = getBlockInChunk(chunk);
            double amountInChunk = getAmountInChunk(requirement, pos);
            if (!isValidChunk(amountInChunk, amountToHandle, requirement)) continue;

            double amountToHandleInChunk = getAmountToApply(amountInChunk, amountToHandle, requirement);
            if (doAction) handleAmount(requirement, pos, amountToHandleInChunk);

            amountToHandle -= amountToHandleInChunk;
        }

        return amountToHandle;
    }

    public BlockPos getBlockInChunk(Chunk chunk) {
        // Get chunk's starting block position
        int chunkStartX = chunk.getPos().x * 16;
        int chunkStartZ = chunk.getPos().z * 16;

        return new BlockPos(chunkStartX, 0, chunkStartZ);
    }

    abstract protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos);
    abstract protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement);
    abstract protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement);
    abstract protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle);
}

