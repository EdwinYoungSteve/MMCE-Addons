package github.alecsio.mmceaddons.common.tile.handler.strategy;

import net.minecraft.world.chunk.Chunk;

import java.util.List;

@FunctionalInterface
public interface ChunkSelectionStrategy {
    Chunk selectChunk(List<Chunk> availableChunks);
}
