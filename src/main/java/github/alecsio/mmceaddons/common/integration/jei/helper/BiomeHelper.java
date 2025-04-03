package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;

import javax.annotation.Nonnull;
import java.util.UUID;

public class BiomeHelper extends BaseIngredientHelper<Biome> {

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull Biome biome) {
        return biome.getRegistryName();
    }

    @Override
    @Nonnull
    public String getResourceId(@Nonnull Biome biome) {
        return getUniqueId(biome);
    }

    @Override
    @Nonnull
    public Biome copyIngredient(@Nonnull Biome biome) {
        return new Biome(biome.getRegistryName(), biome.getName());
    }
}
