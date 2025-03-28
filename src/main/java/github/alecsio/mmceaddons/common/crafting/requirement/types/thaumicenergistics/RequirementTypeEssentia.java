package github.alecsio.mmceaddons.common.crafting.requirement.types.thaumicenergistics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.integration.jei.wrapper.AspectListWrapper;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import kport.modularmagic.common.utils.RequirementUtils;
import thaumcraft.api.aspects.Aspect;

public class RequirementTypeEssentia extends RequirementType<AspectListWrapper, RequirementEssentia> {
    @Override
    public ComponentRequirement<AspectListWrapper, ? extends RequirementType<AspectListWrapper, RequirementEssentia>> createRequirement(IOType type, JsonObject jsonObject) {
        JsonArray essentia = jsonObject.getAsJsonArray("essentia");

        AspectListWrapper essentiaList = new AspectListWrapper();
        for (JsonElement element : essentia) {
            JsonObject essentiaObject = element.getAsJsonObject();
            Aspect aspect = RequirementUtils.getAspect(essentiaObject, "aspect", "yolo");
            int amount = RequirementUtils.getRequiredInt(essentiaObject, "amount", "yolo2");
            essentiaList.add(aspect, amount);
        }

        return new RequirementEssentia(essentiaList, type);
    }
}
