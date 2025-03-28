package github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.RequirementRadiationPerTick;
import github.alecsio.mmceaddons.common.exception.ConsistencyException;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

import javax.annotation.Nullable;

public class RequirementTypeRadiationPerTick extends RequirementType<Radiation, RequirementRadiationPerTick> {
    @Override
    public ComponentRequirement<Radiation, ? extends RequirementType<Radiation, RequirementRadiationPerTick>> createRequirement(IOType ioType, JsonObject jsonObject) {
        if (ioType == IOType.OUTPUT) {throw new ConsistencyException("output requirement type is not supported");}

        int chunkRange = RequirementUtils.getOptionalInt(jsonObject, "chunkRange", 0); // Only the chunk the machine is in

        return new RequirementRadiationPerTick(ioType, chunkRange, Double.MAX_VALUE, 0, Double.MAX_VALUE);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.NUCLEARCRAFT.modid;
    }
}
