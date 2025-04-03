package github.alecsio.mmceaddons.common.integration.jei;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.integration.jei.helper.*;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.*;
import github.alecsio.mmceaddons.common.integration.jei.render.*;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import stanhebben.zenscript.annotations.NotNull;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

    public static IGuiHelper GUI_HELPER;

    @Override
    public void register(IModRegistry registry) {
        GUI_HELPER = registry.getJeiHelpers().getGuiHelper();
    }

    @Override
    public void registerIngredients(@NotNull IModIngredientRegistration registry) {

        registry.register(Dimension.class, Lists.newArrayList(), new DimensionHelper(), new DimensionRenderer());
        registry.register(Biome.class, Lists.newArrayList(), new BiomeHelper(), new BiomeRenderer());

        if (Mods.NUCLEARCRAFT.isPresent()) {
            registry.register(Radiation.class, Lists.newArrayList(), new RadiationHelper(), new RadiationRenderer());
        }

        if (Mods.BLOODMAGIC.isPresent()) {
            registry.register(Meteor.class, Lists.newArrayList(), new MeteorHelper(), new MeteorRenderer());
        }

        if (Mods.THAUMICENERGISTICS.isPresent()) {
            registry.register(Essentia.class, Lists.newArrayList(), new EssentiaHelper(), new EssentiaRenderer());
        }

        if (Mods.THAUMCRAFT.isPresent()) {
            registry.register(Flux.class, Lists.newArrayList(), new FluxHelper(), new FluxRenderer());
        }
    }
}
