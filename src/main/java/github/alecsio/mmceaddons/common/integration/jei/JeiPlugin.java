package github.alecsio.mmceaddons.common.integration.jei;

import WayofTime.bloodmagic.meteor.Meteor;
import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.integration.jei.helper.EssentiaHelper;
import github.alecsio.mmceaddons.common.integration.jei.helper.MeteorHelper;
import github.alecsio.mmceaddons.common.integration.jei.helper.RadiationHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.integration.jei.render.EssentiaRenderer;
import github.alecsio.mmceaddons.common.integration.jei.render.MeteorRenderer;
import github.alecsio.mmceaddons.common.integration.jei.render.RadiationRenderer;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import stanhebben.zenscript.annotations.NotNull;
import thaumicenergistics.api.EssentiaStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

    public static IGuiHelper GUI_HELPER;

    @Override
    public void register(IModRegistry registry) {
        GUI_HELPER = registry.getJeiHelpers().getGuiHelper();
    }

    @Override
    public void registerIngredients(@NotNull IModIngredientRegistration registry) {
        if (Mods.NUCLEARCRAFT.isPresent()) {
            registry.register(Radiation.class, Lists.newArrayList(), new RadiationHelper<>(), new RadiationRenderer());
        }

        if (Mods.BLOODMAGIC.isPresent()) {
            registry.register(Meteor.class, Lists.newArrayList(), new MeteorHelper<>(), new MeteorRenderer());
        }

        if (Mods.THAUMICENERGISTICS.isPresent()) {
            registry.register(EssentiaStack.class, Lists.newArrayList(), new EssentiaHelper(), new EssentiaRenderer());
        }
    }
}
