package github.alecsio.mmceaddons.common.integration.jei.render;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class BiomeRenderer extends BaseIngredientRenderer<Biome> {
    @Override
    public ResourceLocation getTexture(Biome ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_biome.png");
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Biome ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = Lists.newArrayList();

        tooltip.add("Biome Registry Name: " + ingredient.getRegistryName());

        if (ingredient.getName() != null && !ingredient.getName().isEmpty()) tooltip.add("Biome Name: " + ingredient.getName());

        return tooltip;
    }
}
