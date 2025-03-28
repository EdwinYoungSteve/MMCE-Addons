package github.alecsio.mmceaddons.common.integration.jei.component;

import WayofTime.bloodmagic.meteor.Meteor;
import github.alecsio.mmceaddons.common.integration.jei.component.base.JEIComponentBase;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutMeteor;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;

public class JEIComponentMeteor extends JEIComponentBase<Meteor> {

    public JEIComponentMeteor(Meteor meteor) {
        super(meteor, Meteor.class);
    }

    @Override
    public RecipeLayoutPart<Meteor> getLayoutPart(Point offset) {
        return new LayoutMeteor(offset);
    }
}
