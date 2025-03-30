package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.MeteorRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutMeteor extends BaseRecipeLayoutPart<Meteor> {

    public LayoutMeteor(Point offset) {
        super(offset, Meteor.class);
    }

    @Override
    public IIngredientRenderer<Meteor> provideIngredientRenderer() {
        return new MeteorRenderer();
    }
}
