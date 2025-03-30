package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;

public record Flux(float amount) implements IRequiresEquals<Flux> {

    @Override
    public boolean equalsTo(Flux other) {
        return amount == other.amount;
    }
}
