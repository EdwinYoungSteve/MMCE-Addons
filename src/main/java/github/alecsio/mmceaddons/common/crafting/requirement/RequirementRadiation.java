package github.alecsio.mmceaddons.common.crafting.requirement;

import github.alecsio.mmceaddons.common.crafting.component.ComponentRadiation;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.RequirementTypeRadiation;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentRadiation;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.tile.TileRadiationProvider;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentRadiationProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class RequirementRadiation extends ComponentRequirement.PerTick<Radiation, RequirementTypeRadiation> {
    private final double amount;
    private final boolean perTick;

    public RequirementRadiation(IOType actionType, double amount, boolean perTick) {
        super((RequirementTypeRadiation) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_RADIATION), actionType);
        this.amount = amount;
        this.perTick = perTick;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cmp = component.getComponent();
        return cmp.getComponentType() instanceof ComponentRadiation &&
                cmp instanceof MachineComponentRadiationProvider &&
                cmp.ioType == getActionType();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(ProcessingComponent<?> processingComponent, RecipeCraftingContext recipeCraftingContext) {
        if (!perTick) return CraftCheck.success();

        if (getActionType() == IOType.INPUT) {
            TileRadiationProvider radiationProvider = (TileRadiationProvider) processingComponent.getComponent().getContainerProvider();
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> radiationProvider.removeRadiation(amount, recipeCraftingContext.getMachineController().getPos()));
        }
        return CraftCheck.success();
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if (!canStartCrafting(component, context, Collections.emptyList()).isSuccess()) {
            return false;
        }

        if (getActionType() == IOType.INPUT) {
            TileRadiationProvider radiationProvider = (TileRadiationProvider) component.getComponent().getContainerProvider();
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> radiationProvider.removeRadiation(amount, context.getMachineController().getPos()));
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if (getActionType() == IOType.OUTPUT) {
            TileRadiationProvider radiationProvider = (TileRadiationProvider) component.getComponent().getContainerProvider();
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> radiationProvider.addRadiation(amount));
        }
        return CraftCheck.success();
    }

    @Override
    public ComponentRequirement<Radiation, RequirementTypeRadiation> deepCopy() {
        return this;
    }

    @Override
    public ComponentRequirement<Radiation, RequirementTypeRadiation> deepCopyModified(List<RecipeModifier> modifiers) {
        return this;
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "Couldn't find component for requirement";
    }

    @Override
    public JEIComponent<Radiation> provideJEIComponent() {
        return new JEIComponentRadiation(this);
    }

    public double getAmount() {
        return amount;
    }
}
