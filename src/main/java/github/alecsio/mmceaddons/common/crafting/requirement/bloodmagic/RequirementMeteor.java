package github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic;

import WayofTime.bloodmagic.meteor.MeteorRegistry;
import com.google.common.collect.Lists;
import github.alecsio.mmceaddons.common.crafting.component.ComponentMeteor;
import github.alecsio.mmceaddons.common.crafting.helper.MeteorProviderCopy;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic.RequirementTypeMeteor;
import github.alecsio.mmceaddons.common.crafting.requirement.validator.RequirementValidator;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentMeteor;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Meteor;
import github.alecsio.mmceaddons.common.tile.bloodmagic.TileMeteorProvider;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentMeteorProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import kport.modularmagic.common.crafting.helper.LifeEssenceProviderCopy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumicenergistics.container.ActionType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequirementMeteor extends ComponentRequirement.MultiCompParallelizable<Meteor, RequirementTypeMeteor> {

    private static final RequirementValidator requirementValidator = RequirementValidator.getInstance();

    private final Meteor meteor;

    public static RequirementMeteor from(String catalystItem, int amount) {
        Item item = Item.getByNameOrId(catalystItem);
        requirementValidator.validateNotNull(item, String.format("Failed to load recipe for %s. Item %s doesn't exist!", RequirementMeteor.class, catalystItem));
        requirementValidator.validateNotNegative(amount, "Amount must be a positive number!");

        var catalystStack = new ItemStack(item, amount);
        var meteor = MeteorRegistry.getMeteorForItem(catalystStack);
        requirementValidator.validateNotNull(meteor, String.format("Failed to find meteor with catalyst: %s", item.getRegistryName()));

        return new RequirementMeteor(IOType.OUTPUT, new Meteor(catalystStack, meteor.getComponents(), meteor.getExplosionStrength(), meteor.getRadius()));
    }

    public RequirementMeteor(IOType actionType, Meteor meteor) {
        super((RequirementTypeMeteor) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_METEOR), actionType);
        this.meteor = meteor;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cpn = component.getComponent();
        return cpn instanceof MachineComponentMeteorProvider &&
                cpn.getComponentType() instanceof ComponentMeteor &&
                cpn.ioType == getActionType();
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
        return "error.modularmachineryaddons.component.invalid.meteor";
    }

    @Override
    public JEIComponent<Meteor> provideJEIComponent() {
        return new JEIComponentMeteor(this.meteor);
    }

    public Meteor getMeteor() {
        return meteor;
    }

    @Nonnull
    @Override
    public List<ProcessingComponent<?>> copyComponents(List<ProcessingComponent<?>> toCopy) {
        List<ProcessingComponent<?>> copy = new ArrayList<>(toCopy.size());

        toCopy.forEach(component -> {
            MeteorProviderCopy meteorProvider = new MeteorProviderCopy(((MeteorProviderCopy) component.getProvidedComponent()).getOriginal());

            copy.add(new ProcessingComponent<>((MachineComponent<Object>) component.getComponent() , meteorProvider, component.getTag()));
        });

        return copy;
    }

    private static List<MeteorProviderCopy> convertToMeteorProviderCopyList(List<ProcessingComponent<?>> components) {
        return Lists.transform(components, component -> component != null ? ((MeteorProviderCopy) component.getProvidedComponent()) : null);
    }

    @Nonnull
    @Override
    public synchronized CraftCheck canStartCrafting(List<ProcessingComponent<?>> components, RecipeCraftingContext context) {
        List<ComponentRequirement<?, ?>> requirements = context.getRequirementBy(this.requirementType, IOType.OUTPUT);
        List<MeteorProviderCopy> copiedComponents = convertToMeteorProviderCopyList(components);

        // todo: iterate over the requirements instead
        for (int i = 0; i < requirements.size(); i++) {
            MeteorProviderCopy component = copiedComponents.get(i);
            ComponentRequirement<?, ?> requirement = requirements.get(i);

            CraftCheck check = component.canHandle((RequirementMeteor) requirement);

            if (!check.isSuccess()) {
                return check;
            }
        }

        return CraftCheck.success();
    }

    @Override
    public synchronized void startCrafting(List<ProcessingComponent<?>> components, RecipeCraftingContext context, ResultChance chance) {
        List<ComponentRequirement<?, ?>> requirements = context.getRequirementBy(this.requirementType, IOType.OUTPUT);
        List<MeteorProviderCopy> copiedComponents = convertToMeteorProviderCopyList(components);


        for (int i = 0; i < requirements.size(); i++) {
            MeteorProviderCopy component = copiedComponents.get(i);
            ComponentRequirement<?, ?> requirement = requirements.get(i);

            component.handle((RequirementMeteor) requirement);
        }
    }

    @Override
    public void finishCrafting(List<ProcessingComponent<?>> components, RecipeCraftingContext context, ResultChance chance) {

    }

    @Override
    public int getMaxParallelism(List<ProcessingComponent<?>> components, RecipeCraftingContext context, int maxParallelism) {
        if (ignoreOutputCheck && actionType == IOType.OUTPUT) {
            return maxParallelism;
        }


    }
}
