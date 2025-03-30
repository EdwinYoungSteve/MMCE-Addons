package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import com.github.bsideup.jabel.Desugar;
import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;

@Desugar
public record Flux(float amount) implements IRequiresEquals<Flux> {

    @Override
    public boolean equalsTo(Flux other) {
        return amount == other.amount;
    }
}
