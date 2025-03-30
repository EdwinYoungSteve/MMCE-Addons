package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import hellfirepvp.modularmachinery.common.machine.IOType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class RadiationRenderer extends BaseIngredientRenderer<Radiation> {

    @Override
    public ResourceLocation getTexture(Radiation ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/overlay_radiationprovider.png");
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
