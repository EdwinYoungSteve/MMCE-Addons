package github.alecsio.mmceaddons.common.integration.jei.component;

import github.alecsio.mmceaddons.common.integration.jei.component.base.JEIComponentBase;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Dimension;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutDimension;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;

public class JEIComponentDimension extends JEIComponentBase<Dimension> {

    public JEIComponentDimension(Dimension requirement) {
        super(requirement, Dimension.class);
    }

    @Override
    public RecipeLayoutPart<Dimension> getLayoutPart(Point offset) {
        return new LayoutDimension(offset);
    }
}
