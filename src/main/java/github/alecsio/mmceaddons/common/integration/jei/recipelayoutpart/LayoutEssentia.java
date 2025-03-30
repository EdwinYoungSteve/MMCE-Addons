package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Essentia;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.EssentiaRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutEssentia extends BaseRecipeLayoutPart<Essentia> {
    public LayoutEssentia(Point offset) {
        super(offset, Essentia.class);
    }

    @Override
    public IIngredientRenderer<Essentia> provideIngredientRenderer() {
        return new EssentiaRenderer();
    }
}
