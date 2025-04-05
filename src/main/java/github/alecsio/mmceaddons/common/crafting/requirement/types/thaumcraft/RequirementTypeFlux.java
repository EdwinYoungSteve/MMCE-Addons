package github.alecsio.mmceaddons.common.crafting.requirement.types.thaumcraft;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.component.base.RequiresMod;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementFlux;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.base.BaseRequirementType;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

@RequiresMod(Mods.THAUMCRAFT_ID)
public class RequirementTypeFlux extends BaseRequirementType<Flux, RequirementFlux> {

    @Override
    public ComponentRequirement<Flux, ? extends RequirementType<Flux, RequirementFlux>> createRequirement(IOType type, JsonObject jsonObject) {
        float amount = RequirementUtils.getRequiredFloat(jsonObject, "amount", ModularMachineryAddonsRequirements.KEY_REQUIREMENT_FLUX.toString());
        int chunkRange = RequirementUtils.getOptionalInt(jsonObject, "chunkRange", 0); // Only the chunk the machine is in

        return RequirementFlux.from(type, chunkRange, amount);
    }
}
