package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor;

public class MeteorHelper extends BaseIngredientHelper<Meteor> {

    @Override
    public String getDisplayName(Meteor t) {
        return "Meteor";
    }

    @Override
    public String getUniqueId(Meteor t) {
        return "Meteor";
    }

    @Override
    public String getResourceId(Meteor t) {
        return null;
    }

    @Override
    public Meteor copyIngredient(Meteor t) {
        return t;
    }
}
