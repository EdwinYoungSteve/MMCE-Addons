package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor;

import javax.annotation.Nonnull;

public class MeteorHelper extends BaseIngredientHelper<Meteor> {

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull Meteor meteor) {
        return "Meteor";
    }

    @Override
    @Nonnull
    public String getResourceId(@Nonnull Meteor meteor) {
        return getUniqueId(meteor);
    }

    @Override
    @Nonnull
    public Meteor copyIngredient(@Nonnull Meteor meteor) {
        // Yes, I know, the components and so on...
        return new Meteor(meteor.getCatalystStack(), meteor.getComponents(), meteor.getExplosionStrength(), meteor.getRadius());
    }
}
