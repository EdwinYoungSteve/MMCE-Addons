package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.common.integration.jei.JeiPlugin;
import mezz.jei.api.gui.IDrawableBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class BaseIngredientRenderer<T> implements IIngredientRenderer<T> {
    protected static IDrawableBuilder builder;

    @Override
    public void render(Minecraft minecraft, int x, int y, @Nullable T toRender) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();

        RenderHelper.enableGUIStandardItemLighting();

        if (toRender == null) return;

        builder = JeiPlugin.GUI_HELPER.drawableBuilder(getTexture(toRender), 0, 0, 16, 16);
        builder.setTextureSize(16, 16);
        builder.build().draw(minecraft, x, y);

        GlStateManager.popMatrix();
        GlStateManager.disableBlend();

        RenderHelper.disableStandardItemLighting();
    }

    public abstract ResourceLocation getTexture(T ingredient);
}
