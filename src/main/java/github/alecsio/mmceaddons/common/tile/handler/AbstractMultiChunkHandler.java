package github.alecsio.mmceaddons.common.tile.handler;

import github.alecsio.mmceaddons.common.chunks.ChunksReader;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.List;

public abstract class AbstractMultiChunkHandler extends TileColorableMachineComponent {
    private final ChunksReader chunksReader = ChunksReader.getInstance();

    public AbstractMultiChunkHandler() {
    }

    public double handle(IMultiChunkRequirement requirement, BlockPos controllerPos, boolean doAction) {
        List<Chunk> chunks = chunksReader.getSurroundingChunks(world, controllerPos, requirement.getChunkRange());
        int failedChunks = 0;

        double amountToHandle = requirement.getAmount();
        for (Chunk chunk : chunks) {
            if (!chunk.isLoaded()) continue;


            BlockPos randomBlockPosInCurrentChunk = getBlockInChunk(chunk);
            double amountInChunk = getAmountInChunk(requirement, randomBlockPosInCurrentChunk);
            if (!isValidChunk(amountInChunk, amountToHandle, requirement)) {
                failedChunks++;
                continue;
            }

            double amountToHandleInChunk = getAmountToApply(amountInChunk, amountToHandle, requirement);
            if (doAction) {handleAmount(requirement, randomBlockPosInCurrentChunk, amountToHandleInChunk);}
            amountToHandle = Math.max(0, amountToHandle - amountToHandleInChunk);

            if (amountToHandle == 0) break;

            /*
            if (failedChunks == chunks.size()) {
                ModularMachineryAddons.logger.error(
                        String.format(
                                "Inconsistent state detected in %s. %d out of %d chunks failed to update, please report this on GitHub!",
                                this.getClass().getSimpleName(),
                                failedChunks,
                                chunks.size()
                        )
                );
            } else {
                ModularMachineryAddons.logger.error(
                        String.format(
                                "Inconsistent state detected in %s. Expected to be able to add %f but %f was not added. Please report this on GitHub!",
                                this.getClass().getSimpleName(),
                                requirement.getAmount(),
                                amountToHandle
                        )
                );
            }

             */
        }
        return amountToHandle;
    }

    public boolean canHandle(IMultiChunkRequirement requirement, BlockPos controllerPos) {
        return handle(requirement, controllerPos, false) == 0;
    }

    public BlockPos getBlockInChunk(Chunk chunk) {
        // Get chunk's starting block position
        int chunkStartX = chunk.getPos().x * 16;
        int chunkStartZ = chunk.getPos().z * 16;

        // Choose a block at (0,0) within the chunk (e.g., the chunk's first block). This is necessary because WorldDemonWillHandler expects a blockpos
        // and then determines the chunk from that
        return new BlockPos(chunkStartX, 0, chunkStartZ);
    }

    abstract protected double getAmountInChunk(IMultiChunkRequirement requirement, BlockPos randomBlockPos);
    abstract protected boolean isValidChunk(double currentAmount, double amountToModify, IMultiChunkRequirement requirement);
    abstract protected double getAmountToApply(double amountInChunk, double amountToHandle, IMultiChunkRequirement requirement);
    abstract protected void handleAmount(IMultiChunkRequirement requirement, BlockPos controllerPos, double amountToHandle);
}

