package github.alecsio.mmceaddons.common.integration.jei.render;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor;
import github.alecsio.mmceaddons.common.integration.jei.render.base.BaseIngredientRenderer;
import net.minecraft.util.ResourceLocation;

public class MeteorRenderer extends BaseIngredientRenderer<Meteor> {

    @Override
    public ResourceLocation getTexture(Meteor ingredient) {
        return new ResourceLocation(ModularMachineryAddons.MODID, "textures/gui/jei/jei_meteor.png");
    }
}
