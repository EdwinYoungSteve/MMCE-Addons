package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.formatting.FormatUtils;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.formatting.ITooltippable;
import mezz.jei.api.recipe.IIngredientType;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class Biome implements IRequiresEquals<Biome>, IIngredientType<Biome>, ITooltippable {

    private String registryName;
    private String name;

    public Biome() {}

    public Biome(String registryName) {
        this.registryName = registryName;
    }

    public Biome(String registryName, String name) {
        this(registryName);
        this.name = name;
    }

    @Override
    public boolean equalsTo(Biome other) {
        return this.registryName.equals(other.registryName) && Objects.equals(this.name, other.name);
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public Class<? extends Biome> getIngredientClass() {
        return this.getClass();
    }

    @Override
    public List<String> getTooltip() {
        return Lists.newArrayList(FormatUtils.format("Biome", name != null && !name.isEmpty() ? name : registryName));
    }
}
