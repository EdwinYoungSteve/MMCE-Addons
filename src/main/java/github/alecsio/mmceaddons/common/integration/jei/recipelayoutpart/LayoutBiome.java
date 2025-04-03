package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;
import github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart.base.BaseRecipeLayoutPart;
import github.alecsio.mmceaddons.common.integration.jei.render.BiomeRenderer;
import mezz.jei.api.ingredients.IIngredientRenderer;

import java.awt.*;

public class LayoutBiome extends BaseRecipeLayoutPart<Biome> {

    public LayoutBiome(Point offset) {
        super(offset, Biome.class);
    }

    @Override
    public IIngredientRenderer<Biome> provideIngredientRenderer() {
        return new BiomeRenderer();
    }
}
