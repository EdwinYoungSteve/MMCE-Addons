package github.alecsio.mmceaddons.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementDimension;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Dimension;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class RequirementTypeDimension extends RequirementType<Dimension, RequirementDimension> {
    @Override
    public ComponentRequirement<Dimension, ? extends RequirementType<Dimension, RequirementDimension>> createRequirement(IOType type, JsonObject jsonObject) {
        return null;
    }
}
