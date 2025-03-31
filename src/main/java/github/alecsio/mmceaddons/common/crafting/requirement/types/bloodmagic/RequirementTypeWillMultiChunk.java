package github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementWillMultiChunk;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import kport.modularmagic.common.integration.jei.ingredient.DemonWill;
import kport.modularmagic.common.utils.RequirementUtils;

import javax.annotation.Nullable;

public class RequirementTypeWillMultiChunk extends RequirementType<DemonWill, RequirementWillMultiChunk> {

    @Override
    public ComponentRequirement<DemonWill, ? extends RequirementType<DemonWill, RequirementWillMultiChunk>> createRequirement(IOType type, JsonObject json) {
        String willType = RequirementUtils.getRequiredString(json, "will-type", ModularMagicRequirements.KEY_REQUIREMENT_WILL.toString());
        double amount = RequirementUtils.getRequiredDouble(json, "amount", ModularMachineryAddonsRequirements.KEY_REQUIREMENT_WILL_MULTI_CHUNK.toString());
        double min = RequirementUtils.getOptionalDouble(json, "min", 0.0F);
        double max = RequirementUtils.getOptionalDouble(json, "max", 100.0F);
        int chunkRange = RequirementUtils.getOptionalInt(json, "chunkRange", 0); // Only the chunk the machine is in

        return RequirementWillMultiChunk.from(type, chunkRange, amount, min, max, willType);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.BLOODMAGIC.modid;
    }
}
