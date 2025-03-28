package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.integration.JeiPlugin;
import mezz.jei.api.gui.IDrawableBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RadiationRenderer implements IIngredientRenderer<Radiation> {

    private static IDrawableBuilder radiation;

    @Override
    public void render(Minecraft minecraft, int x, int y, @Nullable Radiation radiation) {
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();

        if (radiation == null) return;

        if (RadiationRenderer.radiation == null) {
            ResourceLocation texture = new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/overlay_radiationprovider.png");
            RadiationRenderer.radiation = JeiPlugin.GUI_HELPER.drawableBuilder(texture, 0, 0, 16, 16);
        }
        RadiationRenderer.radiation.setTextureSize(16, 16);
        RadiationRenderer.radiation.build().draw(minecraft, x, y);

        GlStateManager.disableBlend();
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Radiation ingredient) {
        List<String> tooltip = new ArrayList<>();
        if (ingredient.getIoType() == IOType.INPUT) {
            tooltip.add("To Infinity and Beyond!");
        } else {
            tooltip.add(String.format("%s: %s", "Radiation", ingredient.getAmount()));
        }

        return tooltip;
    }
}
