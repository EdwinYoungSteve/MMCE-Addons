package github.alecsio.mmceaddons.common.crafting.requirement.types.projecte;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.crafting.requirement.projecte.RequirementEMC;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.EMC;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

public class RequirementTypeEMC extends RequirementType<EMC, RequirementEMC> {
    @Override
    public ComponentRequirement<EMC, ? extends RequirementType<EMC, RequirementEMC>> createRequirement(IOType type, JsonObject recipe) {
        int amount = RequirementUtils.getRequiredInt(recipe, "amount", "yolo2");

        return new RequirementEMC(new EMC((long) amount), type);
    }
}
