package github.alecsio.mmceaddons.common.tile.handler.strategy;

import net.minecraft.world.chunk.Chunk;

import java.util.List;

public class SequentialChunkSelectionStrategy implements ChunkSelectionStrategy {

    private int lastIndex = -1; // So that the first call starts from the chunk at 0

    @Override
    public Chunk selectChunk(List<Chunk> availableChunks) {
        lastIndex = (lastIndex + 1) % availableChunks.size(); // Move to the next chunk
        return availableChunks.get(lastIndex);
    }
}
