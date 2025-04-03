package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Dimension;

import javax.annotation.Nonnull;
import java.util.UUID;

public class DimensionHelper extends BaseIngredientHelper<Dimension> {

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull Dimension dimension) {
        return String.valueOf(dimension.getId());
    }

    @Override
    @Nonnull
    public String getResourceId(@Nonnull Dimension dimension) {
        return getUniqueId(dimension);
    }

    @Override
    @Nonnull
    public Dimension copyIngredient(@Nonnull Dimension dimension) {
        return new Dimension(dimension.getId(), dimension.getName());
    }
}
