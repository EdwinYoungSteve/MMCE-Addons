package github.alecsio.mmceaddons.common.integration.jei.component;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementWillMultiChunk;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import kport.modularmagic.common.integration.jei.ingredient.DemonWill;
import kport.modularmagic.common.integration.jei.recipelayoutpart.LayoutWill;

import java.awt.*;
import java.util.List;

public class JEIComponentWill extends ComponentRequirement.JEIComponent<DemonWill> {
    private final RequirementWillMultiChunk requirement;

    public JEIComponentWill(RequirementWillMultiChunk requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<DemonWill> getJEIRequirementClass() {
        return DemonWill.class;
    }

    @Override
    public List<DemonWill> getJEIIORequirements() {
        DemonWill demonWill = new DemonWill(requirement.willType, requirement.getAmount());
        return Lists.newArrayList(demonWill);
    }

    @Override
    public RecipeLayoutPart<DemonWill> getLayoutPart(Point offset) {
        return new LayoutWill(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, DemonWill ingredient, List<String> tooltip) {

    }
}
