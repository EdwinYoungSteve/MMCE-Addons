package github.alecsio.mmceaddons.common.integration.jei.component;

import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutEssentia;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import thaumicenergistics.api.EssentiaStack;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentEssentia extends ComponentRequirement.JEIComponent<EssentiaStack> {
    private EssentiaStack essentiaStack;

    public JEIComponentEssentia(RequirementEssentia requirement) {
        this.essentiaStack = requirement.getEssentiaStack();
    }

    @Override
    public RecipeLayoutPart<EssentiaStack> getLayoutPart(Point offset) {
        return new LayoutEssentia(EssentiaStack.class, offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, EssentiaStack ingredient, List<String> tooltip) {

    }

    @Override
    public List<EssentiaStack> getJEIIORequirements() {
        return Collections.singletonList(essentiaStack);
    }

    @Override
    public Class<EssentiaStack> getJEIRequirementClass() {
        return EssentiaStack.class;
    }
}
