package github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft;

import github.alecsio.mmceaddons.common.crafting.component.ComponentRadiation;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft.RequirementTypeRadiationPerTick;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentRadiation;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.tile.TileRadiationProvider;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentRadiationProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementRadiationPerTick extends ComponentRequirement.PerTick<Radiation, RequirementTypeRadiationPerTick> implements IMultiChunkRequirement, IRequirementRadiation {
    private final int chunkRange;
    private final double amount;
    private final double minPerChunk;
    private final double maxPerChunk;

    public RequirementRadiationPerTick(IOType actionType, int chunkRange, double amount, double minPerChunk, double maxPerChunk) {
        super((RequirementTypeRadiationPerTick) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_RADIATION_PER_TICK), actionType);
        this.chunkRange = chunkRange;
        this.amount = amount;
        this.minPerChunk = minPerChunk;
        this.maxPerChunk = maxPerChunk;
    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(ProcessingComponent<?> processingComponent, RecipeCraftingContext recipeCraftingContext) {
        TileRadiationProvider radiationProvider = (TileRadiationProvider) processingComponent.getComponent().getContainerProvider();

        if (!radiationProvider.canHandle(this, recipeCraftingContext.getMachineController().getPos())) {
            return CraftCheck.failure("cannot handle this component"); //todo: fix
        }
        radiationProvider.handle(this, recipeCraftingContext.getMachineController().getPos(), true);
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        TileRadiationProvider radiationProvider = (TileRadiationProvider) component.getComponent().getContainerProvider();

        if (!radiationProvider.canHandle(this, context.getMachineController().getPos())) {
            return CraftCheck.failure("cannot handle this component"); //todo: fix
        }
        return CraftCheck.success();
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> processingComponent, RecipeCraftingContext recipeCraftingContext) {
        MachineComponent<?> cmp = processingComponent.getComponent();
        return cmp.getComponentType() instanceof ComponentRadiation &&
                cmp instanceof MachineComponentRadiationProvider &&
                cmp.ioType == getActionType();
    }

    @Override
    public ComponentRequirement<Radiation, RequirementTypeRadiationPerTick> deepCopy() {
        return new RequirementRadiationPerTick(this.actionType, this.chunkRange, this.amount, this.minPerChunk, this.maxPerChunk);
    }

    @Override
    public ComponentRequirement<Radiation, RequirementTypeRadiationPerTick> deepCopyModified(List<RecipeModifier> list) {
        return deepCopy();
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "";
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
