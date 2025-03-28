package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.RadiationRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutRadiation extends BaseRecipeLayoutPart<Radiation> {

    public LayoutRadiation(Point offset) {
        super(offset, Radiation.class);
    }

    @Override
    public IIngredientRenderer<Radiation> provideIngredientRenderer() {
        return new RadiationRenderer();
    }
}
