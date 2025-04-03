package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;

import java.util.UUID;

public class BiomeHelper extends BaseIngredientHelper<Biome> {
    @Override
    public String getDisplayName(Biome biome) {
        return biome.getRegistryName();
    }

    @Override
    public String getUniqueId(Biome biome) {
        return biome.getRegistryName() + UUID.randomUUID();
    }

    @Override
    public String getResourceId(Biome biome) {
        return getUniqueId(biome);
    }

    @Override
    public Biome copyIngredient(Biome biome) {
        return new Biome(biome.getRegistryName(), biome.getName());
    }
}
