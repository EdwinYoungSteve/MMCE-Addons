package github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementMeteor;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

import javax.annotation.Nullable;

public class RequirementTypeMeteor extends RequirementType<github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor, RequirementMeteor> {
    @Override
    public ComponentRequirement<Meteor, ? extends RequirementType<Meteor, RequirementMeteor>> createRequirement(IOType type, JsonObject jsonObject) {
        String itemString = RequirementUtils.getRequiredString(jsonObject, "item", RequirementMeteor.class.getSimpleName());
        int amount = RequirementUtils.getRequiredInt(jsonObject, "amount", "amount");

        return RequirementMeteor.from(itemString, amount);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.BLOODMAGIC.modid;
    }
}
