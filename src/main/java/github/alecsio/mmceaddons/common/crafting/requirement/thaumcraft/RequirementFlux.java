package github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft;

import github.alecsio.mmceaddons.common.crafting.component.ComponentFlux;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.thaumcraft.RequirementTypeFlux;
import github.alecsio.mmceaddons.common.crafting.requirement.validator.RequirementValidator;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentFlux;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentFluxProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementFlux extends ComponentRequirement<Flux, RequirementTypeFlux> implements IMultiChunkRequirement {

    private static final RequirementValidator requirementValidator = RequirementValidator.getInstance();

    private final int chunkRange;
    private final double amount;

    public static RequirementFlux from(IOType ioType, int chunkRange, float amount) {
        requirementValidator.validateNotNegative(chunkRange, "chunkRange");
        requirementValidator.validateNotNegative(amount, "amount");

        return new RequirementFlux(ioType, chunkRange, amount);
    }

    private RequirementFlux(IOType actionType, int chunkRange, double amount) {
        super((RequirementTypeFlux) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_FLUX), actionType);
        this.chunkRange = chunkRange;
        this.amount = amount;
    }

    @Override
    public IOType getIOType() {
        return actionType;
    }

    @Override
    public int getChunkRange() {
        return this.chunkRange;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public double getMinPerChunk() {
        return 0;
    }

    @Override
    public double getMaxPerChunk() {
        return Float.MAX_VALUE;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cmp = component.getComponent();
        return cmp.getComponentType() instanceof ComponentFlux &&
                cmp instanceof MachineComponentFluxProvider &&
                cmp.ioType == getActionType();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        return getFluxHandler(component).canHandle(this);
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> getFluxHandler(component).handle(this));
        return true;
    }

    @Override
    public ComponentRequirement<Flux, RequirementTypeFlux> deepCopy() {
        return new RequirementFlux(getActionType(), chunkRange, amount);
    }

    @Override
    public ComponentRequirement<Flux, RequirementTypeFlux> deepCopyModified(List<RecipeModifier> modifiers) {
        return deepCopy();
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "error.modularmachineryaddons.component.invalid.flux";
    }

    @Override
    public JEIComponent<Flux> provideJEIComponent() {
        return new JEIComponentFlux(new Flux((float) this.amount, chunkRange), Flux.class); // todo: fix this mess
    }

    @SuppressWarnings("unchecked")
    private IRequirementHandler<RequirementFlux> getFluxHandler(ProcessingComponent<?> component) {
        return (IRequirementHandler<RequirementFlux>) component.getComponent().getContainerProvider();
    }
}
