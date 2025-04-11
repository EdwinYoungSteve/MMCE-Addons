package github.alecsio.mmceaddons.common.crafting.requirement;

import hellfirepvp.modularmachinery.common.machine.IOType;

// I want multiple inheritance
public interface IMultiChunkRequirement {

    IOType getIOType();

    int getChunkRange();

    double getAmount();

    double getMinPerChunk();

    double getMaxPerChunk();
}
