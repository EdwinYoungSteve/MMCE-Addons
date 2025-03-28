package github.alecsio.mmceaddons.common.integration.jei.render;

import WayofTime.bloodmagic.meteor.Meteor;
import WayofTime.bloodmagic.meteor.MeteorComponent;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import kport.modularmagic.common.integration.JeiPlugin;
import mezz.jei.api.gui.IDrawableBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeteorRenderer implements IIngredientRenderer<Meteor> {
    private static IDrawableBuilder meteor;

    @Override
    public void render(Minecraft minecraft, int x, int y, @Nullable Meteor meteor) {
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();

        if (meteor == null) return;

        if (MeteorRenderer.meteor == null) {
            ResourceLocation texture = new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_meteor.png");
            MeteorRenderer.meteor = JeiPlugin.GUI_HELPER.drawableBuilder(texture, 0, 0, 16, 16);
        }
        MeteorRenderer.meteor.setTextureSize(16, 16);
        MeteorRenderer.meteor.build().draw(minecraft, x, y);

        GlStateManager.disableBlend();
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Meteor meteor, ITooltipFlag tooltipFlag) {
        String title = "§lMeteor Contents:";
        List<String> tooltip = new ArrayList<>();
        tooltip.add(title);

        double total = meteor.getComponents().stream().mapToDouble(MeteorComponent::getWeight).sum();
        tooltip.addAll(meteor.getComponents().stream().map(component -> String.format("§7%s §8- §7%.2f %%", component.getOreName(), (double)Math.round(component.weight/total*100))).collect(Collectors.toList()));
        return tooltip;
    }
}
