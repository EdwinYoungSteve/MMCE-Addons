package github.alecsio.mmceaddons.common.crafting.requirement.types.thaumcraft;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementVis;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Vis;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

public class RequirementTypeVis extends RequirementType<Vis, RequirementVis> {
    @Override
    public ComponentRequirement<Vis, ? extends RequirementType<Vis, RequirementVis>> createRequirement(IOType type, JsonObject jsonObject) {
        float amount = RequirementUtils.getRequiredFloat(jsonObject, "amount", ModularMachineryAddonsRequirements.KEY_REQUIREMENT_VIS.toString());
        int chunkRange = RequirementUtils.getOptionalInt(jsonObject, "chunkRange", 0); // Only the chunk the machine is in

        return new RequirementVis(type, chunkRange, amount);
    }
}
