package github.alecsio.mmceaddons.common.integration.jei.component;

import github.alecsio.mmceaddons.common.integration.jei.component.base.JEIComponentBase;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Vis;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutVis;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;

public class JEIComponentVis extends JEIComponentBase<Vis> {
    public JEIComponentVis(Vis requirement) {
        super(requirement, Vis.class);
    }

    @Override
    public RecipeLayoutPart<Vis> getLayoutPart(Point offset) {
        return new LayoutVis(offset);
    }
}
