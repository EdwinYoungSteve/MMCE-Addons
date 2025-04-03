package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.util.ResourceLocation;

public class RadiationRenderer extends BaseIngredientRenderer<Radiation> {

    @Override
    public ResourceLocation getTexture(Radiation ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/overlay_radiationprovider.png");
    }
}
