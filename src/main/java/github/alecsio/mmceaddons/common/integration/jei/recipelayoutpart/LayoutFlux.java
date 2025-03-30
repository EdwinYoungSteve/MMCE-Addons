package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.FluxRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutFlux extends BaseRecipeLayoutPart<Flux> {
    public LayoutFlux(Point offset, Class<Flux> clazz) {
        super(offset, clazz);
    }

    @Override
    public IIngredientRenderer<Flux> provideIngredientRenderer() {
        return new FluxRenderer();
    }
}
