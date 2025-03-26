package github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.RequirementRadiation;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

import javax.annotation.Nullable;

public class RequirementTypeRadiation extends RequirementType<Radiation, RequirementRadiation> {
    @Override
    public ComponentRequirement<Radiation, ? extends RequirementType<Radiation, RequirementRadiation>> createRequirement(IOType type, JsonObject jsonObject) {
        double amount = RequirementUtils.getRequiredDouble(jsonObject, "amount", ModularMachineryAddonsRequirements.KEY_REQUIREMENT_RADIATION.toString());
        int chunkRange = RequirementUtils.getOptionalInt(jsonObject, "chunkRange", 0); // Only the chunk the machine is in
        return new RequirementRadiation(type, chunkRange, amount, 0, Double.MAX_VALUE);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.NUCLEARCRAFT.modid;
    }
}
