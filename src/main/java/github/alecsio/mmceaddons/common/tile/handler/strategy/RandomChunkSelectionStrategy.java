package github.alecsio.mmceaddons.common.tile.handler.strategy;

import net.minecraft.world.chunk.Chunk;

import java.util.List;
import java.util.Random;

public class RandomChunkSelectionStrategy implements ChunkSelectionStrategy {

    private final Random random = new Random();

    @Override
    public Chunk selectChunk(List<Chunk> availableChunks) {
        return availableChunks.get(random.nextInt(availableChunks.size()));
    }
}
