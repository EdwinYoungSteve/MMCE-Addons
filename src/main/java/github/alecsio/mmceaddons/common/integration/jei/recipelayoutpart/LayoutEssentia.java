package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.EssentiaRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;
import thaumicenergistics.api.EssentiaStack;

import java.awt.*;

public class LayoutEssentia extends BaseRecipeLayoutPart<EssentiaStack> {
    public LayoutEssentia(Point offset) {
        super(offset, EssentiaStack.class);
    }

    @Override
    public IIngredientRenderer<EssentiaStack> provideIngredientRenderer() {
        return new EssentiaRenderer();
    }
}
