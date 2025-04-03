package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Dimension;

import java.util.UUID;

public class DimensionHelper extends BaseIngredientHelper<Dimension> {
    @Override
    public String getDisplayName(Dimension dimension) {
        return String.valueOf(dimension.getId());
    }

    @Override
    public String getUniqueId(Dimension dimension) {
        return getDisplayName(dimension) + UUID.randomUUID();
    }

    @Override
    public String getResourceId(Dimension dimension) {
        return getUniqueId(dimension);
    }

    @Override
    public Dimension copyIngredient(Dimension dimension) {
        return new Dimension(dimension.getId(), dimension.getName());
    }
}
