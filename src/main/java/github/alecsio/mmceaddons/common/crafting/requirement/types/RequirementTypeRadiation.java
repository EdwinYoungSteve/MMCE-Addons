package github.alecsio.mmceaddons.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementRadiation;
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
        boolean perTick = RequirementUtils.getOptionalBoolean(jsonObject, "perTick", false);
        return new RequirementRadiation(type, amount, perTick);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.NUCLEARCRAFT.modid;
    }
}
