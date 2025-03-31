package github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft;

import github.alecsio.mmceaddons.common.crafting.component.ComponentRadiation;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.Validator.RequirementValidator;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft.RequirementTypeScrubber;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentRadiation;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentRadiationProvider;
import github.alecsio.mmceaddons.common.tile.nuclearcraft.TileScrubberProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementScrubber extends ComponentRequirement.PerTick<Radiation, RequirementTypeScrubber> implements IMultiChunkRequirement, IRequirementRadiation {

    private static final RequirementValidator requirementValidator = RequirementValidator.getInstance();

    private final int chunkRange;
    private final double amount;
    private final double minPerChunk;
    private final double maxPerChunk;

    public static RequirementScrubber from(int chunkRange) {
        requirementValidator.validateNotNegative(chunkRange, "Chunk range must be a positive number!");

        return new RequirementScrubber(IOType.INPUT, chunkRange, Double.MAX_VALUE, 0, Double.MAX_VALUE);
    }

    private RequirementScrubber(IOType actionType, int chunkRange, double amount, double minPerChunk, double maxPerChunk) {
        super((RequirementTypeScrubber) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_RADIATION_PER_TICK), actionType);
        this.chunkRange = chunkRange;
        this.amount = amount;
        this.minPerChunk = minPerChunk;
        this.maxPerChunk = maxPerChunk;
    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(ProcessingComponent<?> processingComponent, RecipeCraftingContext recipeCraftingContext) {
        TileScrubberProvider scrubberProvider = getProviderFrom(processingComponent);

        if (!scrubberProvider.canHandle(this, recipeCraftingContext.getMachineController().getPos())) {
            return CraftCheck.failure("cannot handle this component"); //todo: fix
        }
        scrubberProvider.handle(this, recipeCraftingContext.getMachineController().getPos(), true);
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
    public ComponentRequirement<Radiation, RequirementTypeScrubber> deepCopy() {
        return RequirementScrubber.from(chunkRange);
    }

    @Override
    public ComponentRequirement<Radiation, RequirementTypeScrubber> deepCopyModified(List<RecipeModifier> list) {
        return deepCopy();
    }

    private TileScrubberProvider getProviderFrom(ProcessingComponent<?> component) {
        return (TileScrubberProvider) component.getComponent().getContainerProvider();
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
    public boolean isPerTick() {
        return true;
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
