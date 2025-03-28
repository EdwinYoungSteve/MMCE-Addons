package github.alecsio.mmceaddons.common.integration.jei.component;

import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutEssentia;
import github.alecsio.mmceaddons.common.integration.jei.wrapper.AspectListWrapper;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentEssentia extends ComponentRequirement.JEIComponent<AspectListWrapper>{
    private final RequirementEssentia essentia;

    public JEIComponentEssentia(RequirementEssentia essentia) {
        this.essentia = essentia;
    }

    @Override
    public Class<AspectListWrapper> getJEIRequirementClass() {
        return AspectListWrapper.class;
    }

    @Override
    public List<AspectListWrapper> getJEIIORequirements() {
        AspectListWrapper essentiaList = new AspectListWrapper();
        essentia.getEssentiaList().aspects.forEach(essentiaList::add);
        return Collections.singletonList(essentiaList);
    }

    @Override
    public RecipeLayoutPart<AspectListWrapper> getLayoutPart(Point offset) {
        return new LayoutEssentia(offset, new AspectListWrapper(this.essentia.getEssentiaList()));
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, AspectListWrapper ingredient, List<String> tooltip) {

    }
}
