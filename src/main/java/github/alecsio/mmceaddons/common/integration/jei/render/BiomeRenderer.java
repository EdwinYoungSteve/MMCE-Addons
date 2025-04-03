package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.util.ResourceLocation;

public class BiomeRenderer extends BaseIngredientRenderer<Biome> {

    @Override
    public ResourceLocation getTexture(Biome ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_biome.png");
    }
}
