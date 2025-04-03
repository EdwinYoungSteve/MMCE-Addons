package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;

import javax.annotation.Nonnull;

public class RadiationHelper extends BaseIngredientHelper<Radiation> {

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull Radiation t) {
        return "Radiation";
    }

    @Override
    @Nonnull
    public String getResourceId(@Nonnull Radiation t) {
        return getUniqueId(t);
    }

    @Override
    @Nonnull
    public Radiation copyIngredient(@Nonnull Radiation t) {
        return new Radiation(t.getAmount(), t.getChunkRange(), t.isScrubber());
    }
}
