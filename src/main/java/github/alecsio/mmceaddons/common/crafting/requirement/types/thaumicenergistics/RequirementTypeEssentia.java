package github.alecsio.mmceaddons.common.crafting.requirement.types.thaumicenergistics;

import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.component.base.RequiresMod;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.crafting.requirement.types.base.BaseRequirementType;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Essentia;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;

@RequiresMod(Mods.THAUMICENERGISTICS_ID)
public class RequirementTypeEssentia extends BaseRequirementType<Essentia, RequirementEssentia> {

    @Override
    public ComponentRequirement<Essentia, ? extends RequirementType<Essentia, RequirementEssentia>> createRequirement(IOType type, JsonObject recipe) {
        String aspect = RequirementUtils.getRequiredString(recipe, "aspect", "yolo");
        int amount = RequirementUtils.getRequiredInt(recipe, "amount", "yolo2");

        return RequirementEssentia.from(type, aspect, amount);
    }
}
