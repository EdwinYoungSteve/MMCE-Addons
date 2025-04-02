package github.alecsio.mmceaddons.common.integration.jei.component;

import github.alecsio.mmceaddons.common.integration.jei.component.base.JEIComponentBase;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.LayoutBiome;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;

public class JEIComponentBiome extends JEIComponentBase<Biome> {

    public JEIComponentBiome(Biome requirement) {
        super(requirement, Biome.class);
    }

    @Override
    public RecipeLayoutPart<Biome> getLayoutPart(Point offset) {
        return new LayoutBiome(offset);
    }
}
