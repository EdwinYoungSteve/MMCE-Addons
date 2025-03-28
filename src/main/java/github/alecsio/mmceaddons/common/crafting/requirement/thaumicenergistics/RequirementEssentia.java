package github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics;

import github.alecsio.mmceaddons.common.crafting.component.ComponentEssentia;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.thaumicenergistics.RequirementTypeEssentia;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentEssentia;
import github.alecsio.mmceaddons.common.integration.jei.wrapper.AspectListWrapper;
import github.alecsio.mmceaddons.common.tile.handler.IEssentiaHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentEssentiaProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementEssentia extends ComponentRequirement<AspectListWrapper, RequirementTypeEssentia> {
    private final AspectList essentiaList;

    public RequirementEssentia(AspectList essentiaList, IOType type) {
        super((RequirementTypeEssentia) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_ESSENTIA), type);
        this.essentiaList = essentiaList;
    }

    public AspectList getEssentiaList() {
        return essentiaList;
    }


    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cmp = component.getComponent();
        return cmp.getComponentType() instanceof ComponentEssentia &&
                        cmp instanceof MachineComponentEssentiaProvider &&
                        cmp.ioType == getActionType();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        IEssentiaHandler essentiaHandler = (IEssentiaHandler) component.getComponent().getContainerProvider();
        return essentiaHandler.canHandle(this);
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        IEssentiaHandler essentiaHandler = (IEssentiaHandler) component.getComponent().getContainerProvider();
        return essentiaHandler.handle(this);
    }

    @Override
    public ComponentRequirement<AspectListWrapper, RequirementTypeEssentia> deepCopy() {
        return this;
    }

    @Override
    public ComponentRequirement<AspectListWrapper, RequirementTypeEssentia> deepCopyModified(List<RecipeModifier> modifiers) {
        return this;
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "";
    }

    @Override
    public JEIComponent<AspectListWrapper> provideJEIComponent() {
        return new JEIComponentEssentia(this);
    }


}
