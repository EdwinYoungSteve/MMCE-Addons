package github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft;

import github.alecsio.mmceaddons.common.crafting.component.ComponentRadiation;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft.RequirementTypeRadiation;
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

public class RequirementRadiation extends ComponentRequirement<Radiation, RequirementTypeRadiation> implements IMultiChunkRequirement, IRequirementRadiation {
    private final int chunkRange;
    private final double amount;
    private final double minPerChunk;
    private final double maxPerChunk;

    public RequirementRadiation(IOType actionType, int chunkRange, double amount, double minPerChunk, double maxPerChunk) {
        super((RequirementTypeRadiation) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_RADIATION), actionType);
        this.chunkRange = chunkRange;
        this.amount = amount;
        this.minPerChunk = minPerChunk;
        this.maxPerChunk = maxPerChunk;
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
        TileRadiationProvider radiationProvider = (TileRadiationProvider) component.getComponent().getContainerProvider();

        if (!radiationProvider.canHandle(this, context.getMachineController().getPos())) {
            return CraftCheck.failure("cannot handle this component"); // todo fix:
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
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> radiationProvider.handle(this, context.getMachineController().getPos(), true));
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if (getActionType() == IOType.OUTPUT) {
            TileRadiationProvider radiationProvider = (TileRadiationProvider) component.getComponent().getContainerProvider();
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> radiationProvider.handle(this, context.getMachineController().getPos(), true));
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

    @Override
    public int getChunkRange() {
        return chunkRange;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public IOType getType() {
        return actionType;
    }

    @Override
    public double getMinPerChunk() {
        return minPerChunk;
    }

    @Override
    public double getMaxPerChunk() {
        return maxPerChunk;
    }
}
