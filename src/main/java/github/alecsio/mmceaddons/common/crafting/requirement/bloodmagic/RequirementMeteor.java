package github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic;

import WayofTime.bloodmagic.meteor.Meteor;
import github.alecsio.mmceaddons.common.crafting.component.ComponentMeteor;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic.RequirementTypeMeteor;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentMeteor;
import github.alecsio.mmceaddons.common.tile.TileMeteorProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementMeteor extends ComponentRequirement<Meteor, RequirementTypeMeteor> {

    private final Meteor meteor;

    public RequirementMeteor(IOType actionType, Meteor meteor) {
        super((RequirementTypeMeteor) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_METEOR), actionType);
        this.meteor = meteor;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cpn = component.getComponent();
        return cpn.getContainerProvider() instanceof TileMeteorProvider &&
                cpn.getComponentType() instanceof ComponentMeteor &&
                cpn.ioType == getActionType();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        TileMeteorProvider tileMeteorProvider = (TileMeteorProvider) component.getComponent().getContainerProvider();
        return tileMeteorProvider.canHandle(meteor);
    }

    @Nonnull
    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if (getActionType() == IOType.OUTPUT) {
            TileMeteorProvider tileMeteorProvider = (TileMeteorProvider) component.getComponent().getContainerProvider();
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> tileMeteorProvider.handle(this.meteor));
        }

        return CraftCheck.success();
    }

    @Override
    public ComponentRequirement<Meteor, RequirementTypeMeteor> deepCopy() {
        // I know, the components, but whatever
        return new RequirementMeteor(actionType, new Meteor(meteor.getCatalystStack(), meteor.getComponents(), meteor.getExplosionStrength(), meteor.getRadius()));
    }

    @Override
    public ComponentRequirement<Meteor, RequirementTypeMeteor> deepCopyModified(List<RecipeModifier> modifiers) {
        return deepCopy();
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "";
    }

    @Override
    public JEIComponent<Meteor> provideJEIComponent() {
        return new JEIComponentMeteor(this.meteor);
    }
}
