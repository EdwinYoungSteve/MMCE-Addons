package github.alecsio.mmceaddons.common.integration.jei.render;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.integration.jei.wrapper.AspectListWrapper;
import kport.modularmagic.common.integration.JeiPlugin;
import mezz.jei.api.gui.IDrawableBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class EssentiaRenderer implements IIngredientRenderer<AspectListWrapper> {

    private static IDrawableBuilder aspectRender;
    private static Aspect aspect;

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable AspectListWrapper ingredient) {
        if (ingredient == null) return;

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();

        // Render each aspect in the list, adjusting positions dynamically
        Aspect[] aspects = ingredient.getAspects();
        int offsetX = 0; // Track horizontal offset for each aspect icon

        for (Aspect aspect : aspects) {
            Color color = new Color(aspect.getColor());
            GlStateManager.color((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F, (float) color.getBlue() / 255.0F, 1.0F);

            // Build and cache drawable if necessary
            if (aspectRender == null || this.aspect == null || this.aspect != aspect) {
                EssentiaRenderer.aspect = aspect;
                ResourceLocation texture = aspect.getImage();
                aspectRender = JeiPlugin.GUI_HELPER.drawableBuilder(texture, 0, 0, 16, 16);
            }

            // Render the aspect icon at adjusted position
            aspectRender.setTextureSize(16, 16);
            aspectRender.build().draw(minecraft, xPosition + offsetX, yPosition);

            // Increase horizontal offset to prevent overlap
            offsetX += 18; // Add padding between icons (16px icon + 2px gap)
        }

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, AspectListWrapper ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList(String.format("%s: %d", ingredient.getAspects()[0].getName(), ingredient.getAmount(aspect)));
    }

    @Override
    public FontRenderer getFontRenderer(Minecraft minecraft, AspectListWrapper ingredient) {
        return IIngredientRenderer.super.getFontRenderer(minecraft, ingredient);
    }
}
