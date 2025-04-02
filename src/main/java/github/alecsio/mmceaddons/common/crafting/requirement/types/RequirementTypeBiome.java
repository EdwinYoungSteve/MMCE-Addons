package github.alecsio.mmceaddons.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementBiome;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Biome;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class RequirementTypeBiome extends RequirementType<Biome, RequirementBiome> {
    @Override
    public ComponentRequirement<Biome, ? extends RequirementType<Biome, RequirementBiome>> createRequirement(IOType type, JsonObject jsonObject) {
        return null;
    }
}
