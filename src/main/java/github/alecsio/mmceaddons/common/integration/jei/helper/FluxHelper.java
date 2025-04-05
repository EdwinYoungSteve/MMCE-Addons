package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;

import javax.annotation.Nonnull;

public class FluxHelper extends BaseIngredientHelper<Flux> {

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull Flux flux) {
        return getDisplayName(flux);
    }

    @Override
    @Nonnull
    public String getResourceId(@Nonnull Flux flux) {
        return getUniqueId(flux);
    }

    @Override
    @Nonnull
    public Flux copyIngredient(@Nonnull Flux flux) {
        return new Flux(flux.amount(), flux.chunkRange());
    }
}
