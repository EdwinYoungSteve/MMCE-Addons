package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;

public class Biome implements IRequiresEquals<Biome> {

    private String registryName;
    private String name;

    public Biome(String registryName) {
        this.registryName = registryName;
    }

    public Biome(String registryName, String name) {
        this(registryName);
        this.name = name;
    }

    @Override
    public boolean equalsTo(Biome other) {
        return this.registryName.equals(other.registryName) && this.name.equals(other.name);
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getName() {
        return name;
    }
}
