package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.common.integration.jei.ingredient.Vis;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class VisRenderer extends BaseIngredientRenderer<Vis> {
    @Override
    public ResourceLocation getTexture(Vis ingredient) {
        return null;
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Vis ingredient, ITooltipFlag tooltipFlag) {
        return super.getTooltip(minecraft, ingredient, tooltipFlag);
    }
}
