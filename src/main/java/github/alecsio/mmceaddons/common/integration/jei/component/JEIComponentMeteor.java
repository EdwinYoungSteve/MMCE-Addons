package github.alecsio.mmceaddons.common.integration.jei.component;

import WayofTime.bloodmagic.meteor.Meteor;
import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutMeteor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.List;

public class JEIComponentMeteor extends ComponentRequirement.JEIComponent<Meteor> {
    private final Meteor meteor;

    public JEIComponentMeteor(Meteor meteor) {
        this.meteor = meteor;
    }

    @Override
    public Class<Meteor> getJEIRequirementClass() {
        return Meteor.class;
    }

    @Override
    public List<Meteor> getJEIIORequirements() {
        return Lists.newArrayList(meteor);
    }

    @Override
    public RecipeLayoutPart<Meteor> getLayoutPart(Point offset) {
        return new LayoutMeteor(offset);
    }


    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, Meteor ingredient, List<String> tooltip) {

    }
}
