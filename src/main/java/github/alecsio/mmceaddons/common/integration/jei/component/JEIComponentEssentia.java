package github.alecsio.mmceaddons.common.integration.jei.component;

import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.integration.jei.component.base.JEIComponentBase;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutEssentia;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import thaumicenergistics.api.EssentiaStack;

import java.awt.*;

public class JEIComponentEssentia extends JEIComponentBase<EssentiaStack> {

    public JEIComponentEssentia(RequirementEssentia requirement) {
        super(requirement.getEssentiaStack(), EssentiaStack.class);
    }

    @Override
    public RecipeLayoutPart<EssentiaStack> getLayoutPart(Point offset) {
        return new LayoutEssentia(offset);
    }
}
