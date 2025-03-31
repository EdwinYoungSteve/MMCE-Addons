package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;

public class Vis implements IRequiresEquals<Vis> {

    private final float amount;
    private final int chunkRange;

    public Vis(float amount, int chunkRange) {
        this.amount = amount;
        this.chunkRange = chunkRange;
    }

    @Override
    public boolean equalsTo(Vis other) {
        return this.amount == other.amount && this.chunkRange == other.chunkRange;
    }

    public float getAmount() {
        return amount;
    }

    public int getChunkRange() {
        return chunkRange;
    }
}
