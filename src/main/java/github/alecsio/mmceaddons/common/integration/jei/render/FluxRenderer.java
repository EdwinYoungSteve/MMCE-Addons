package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.util.ResourceLocation;

public class FluxRenderer extends BaseIngredientRenderer<Flux> {

    @Override
    public ResourceLocation getTexture(Flux ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_flux.png");
    }
}
