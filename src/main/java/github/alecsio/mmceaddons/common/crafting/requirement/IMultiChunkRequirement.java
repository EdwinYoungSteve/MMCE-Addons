package github.alecsio.mmceaddons.common.crafting.requirement;

// I want multiple inheritance
public interface IMultiChunkRequirement {
    int getChunkRange();

    double getAmount();

    double getMinPerChunk();

    double getMaxPerChunk();
}
