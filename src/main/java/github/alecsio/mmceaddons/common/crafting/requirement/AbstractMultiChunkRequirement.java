package github.alecsio.mmceaddons.common.crafting.requirement;

import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public abstract class AbstractMultiChunkRequirement<A, B extends RequirementType<A, ? extends ComponentRequirement<A, B>>> extends ComponentRequirement<A, B> {
    private final int chunkRange;
    private final double amount;
    private final double minPerChunk;
    private final double maxPerChunk;

    public AbstractMultiChunkRequirement(B requirementType, IOType actionType, int chunkRange, double amount, double minPerChunk, double maxPerChunk) {
        super(requirementType, actionType);
        this.chunkRange = chunkRange;
        this.amount = amount;
        this.minPerChunk = minPerChunk;
        this.maxPerChunk = maxPerChunk;
    }

    public int getChunkRange() {
        return chunkRange;
    }

    public double getAmount() {
        return amount;
    }

    public double getMinPerChunk() {
        return minPerChunk;
    }

    public double getMaxPerChunk() {
        return maxPerChunk;
    }
}
