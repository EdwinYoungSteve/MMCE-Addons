package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Vis;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.VisRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutVis extends BaseRecipeLayoutPart<Vis> {
    public LayoutVis(Point offset) {
        super(offset, Vis.class);
    }

    @Override
    public IIngredientRenderer<Vis> provideIngredientRenderer() {
        return new VisRenderer();
    }
}
