package github.alecsio.mmceaddons.common.integration.jei.component;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.IRequirementRadiation;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutRadiation;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.List;

public class JEIComponentRadiation extends ComponentRequirement.JEIComponent<Radiation> {
    private final Radiation radiation;

    public JEIComponentRadiation(IRequirementRadiation requirementRadiation) {
        radiation = new Radiation(requirementRadiation.getAmount(), requirementRadiation.getType());
    }

    @Override
    public Class<Radiation> getJEIRequirementClass() {
        return Radiation.class;
    }

    @Override
    public List<Radiation> getJEIIORequirements() {
        return Lists.newArrayList( radiation);
    }

    @Override
    public RecipeLayoutPart<Radiation> getLayoutPart(Point point) {
        return new LayoutRadiation(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, Radiation radiation, List<String> list) {

    }
}
