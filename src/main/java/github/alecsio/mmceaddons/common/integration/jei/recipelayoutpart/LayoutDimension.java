package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Dimension;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.DimensionRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutDimension extends BaseRecipeLayoutPart<Dimension> {

    public LayoutDimension(Point offset) {
        super(offset, Dimension.class);
    }

    @Override
    public IIngredientRenderer<Dimension> provideIngredientRenderer() {
        return new DimensionRenderer();
    }
}
