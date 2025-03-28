package github.alecsio.mmceaddons.common.integration.jei.render;

import com.google.common.collect.Lists;
import kport.modularmagic.common.integration.JeiPlugin;
import mezz.jei.api.gui.IDrawableBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.api.EssentiaStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class EssentiaRenderer extends BaseIngredientRenderer<EssentiaStack> {
    private static IDrawableBuilder aspectRender;
    private static Aspect aspect;


    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable EssentiaStack ingredient) {
        if (ingredient == null) {
            return;
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();

        Color c = new Color(ingredient.getAspect().getColor());
        GlStateManager.color((float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F, 1.0F);

        if (aspectRender == null || aspect == null || aspectRender != ingredient.getAspect()) {
            aspect = ingredient.getAspect();
            ResourceLocation texture = aspect.getImage();
            aspectRender = JeiPlugin.GUI_HELPER.drawableBuilder(texture, 0, 0, 16, 16);
        }
        aspectRender.setTextureSize(16, 16);
        aspectRender.build().draw(minecraft, xPosition, yPosition);

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    public ResourceLocation getTexture(EssentiaStack ingredient) {
        return ingredient.getAspect().getImage();
    }

    @Override
    @Nonnull
    public List<String> getTooltip(@Nonnull Minecraft minecraft, EssentiaStack ingredient, @Nonnull ITooltipFlag tooltipFlag) {
        return Lists.newArrayList(String.format("%s%s %s: %s%d", TextFormatting.BOLD, ingredient.getAspect().getName(), TextFormatting.GRAY, TextFormatting.GRAY, ingredient.getAmount()));
    }
}
