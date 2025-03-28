package github.alecsio.mmceaddons.common.crafting.requirement.types.thaumicenergistics;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.api.EssentiaStack;

import javax.annotation.Nullable;

public class RequirementTypeEssentia extends RequirementType<EssentiaStack, RequirementEssentia> {
    @Override
    public ComponentRequirement<EssentiaStack, ? extends RequirementType<EssentiaStack, RequirementEssentia>> createRequirement(IOType type, JsonObject recipe) {
        Aspect aspect = RequirementUtils.getAspect(recipe, "aspect", "yolo");
        int amount = RequirementUtils.getRequiredInt(recipe, "amount", "yolo2");

        return new RequirementEssentia(new EssentiaStack(aspect, amount), type);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return Mods.THAUMICENERGISTICS.modid;
    }
}
