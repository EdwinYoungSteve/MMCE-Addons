package github.alecsio.mmceaddons.common.integration.jei.render;

import WayofTime.bloodmagic.meteor.Meteor;
import WayofTime.bloodmagic.meteor.MeteorComponent;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeteorRenderer extends BaseIngredientRenderer<Meteor> {

    @Override
    public ResourceLocation getTexture(Meteor ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_meteor.png");
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
