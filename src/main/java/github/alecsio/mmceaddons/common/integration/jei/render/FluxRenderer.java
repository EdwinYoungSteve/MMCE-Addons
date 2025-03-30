package github.alecsio.mmceaddons.common.integration.jei.render;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class FluxRenderer extends BaseIngredientRenderer<Flux> {
    @Override
    public ResourceLocation getTexture(Flux ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_flux.png");
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Flux ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList(String.format("%sFlux %s: %s%.2f", TextFormatting.DARK_PURPLE, TextFormatting.GRAY, TextFormatting.LIGHT_PURPLE, ingredient.amount()));
    }
}
