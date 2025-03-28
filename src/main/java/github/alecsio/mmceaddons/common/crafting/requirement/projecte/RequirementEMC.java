package github.alecsio.mmceaddons.common.crafting.requirement.projecte;

import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.projecte.RequirementTypeEMC;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.EMC;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementEMC extends ComponentRequirement<EMC, RequirementTypeEMC> {
    private final EMC emc;
    public RequirementEMC(EMC emc, IOType type) {
        super((RequirementTypeEMC) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_EMC), type);
        this.emc = emc;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent component, RecipeCraftingContext ctx) {
        return false;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent component, RecipeCraftingContext context, List restrictions) {
        return null;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return this;
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "";
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return null;
    }

    @Override
    public ComponentRequirement deepCopyModified(List list) {
        return this;
    }
}
