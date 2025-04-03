package github.alecsio.mmceaddons.common.integration.jei.render;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Dimension;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class DimensionRenderer extends BaseIngredientRenderer<Dimension> {

    @Override
    public ResourceLocation getTexture(Dimension ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_dimension.png");
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Dimension dimension, ITooltipFlag tooltipFlag) {
        List<String> tooltip = Lists.newArrayList();

        tooltip.add("Dimension ID: " + dimension.getId());

        if (dimension.getName() != null && !dimension.getName().isEmpty()) tooltip.add("Dimension Name: " + dimension.getName());

         return tooltip;
    }
}
