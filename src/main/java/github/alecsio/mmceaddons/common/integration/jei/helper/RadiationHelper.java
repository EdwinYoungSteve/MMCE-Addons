package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;

public class RadiationHelper extends BaseIngredientHelper<Radiation> {

    @Override
    public String getDisplayName(Radiation t) {
        return "Radiation";
    }

    @Override
    public String getUniqueId(Radiation t) {
        return "Radiation";
    }

    @Override
    public String getResourceId(Radiation t) {
        return null;
    }

    @Override
    public Radiation copyIngredient(Radiation t) {
        return t;
    }
}
