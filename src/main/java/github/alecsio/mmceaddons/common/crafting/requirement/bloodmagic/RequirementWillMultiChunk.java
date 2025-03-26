package github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import github.alecsio.mmceaddons.common.crafting.component.ComponentWillMultiChunk;
import github.alecsio.mmceaddons.common.crafting.requirement.IMultiChunkRequirement;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic.RequirementTypeWillMultiChunk;
import github.alecsio.mmceaddons.common.integration.jei.component.JEIComponentWill;
import github.alecsio.mmceaddons.common.tile.TileWillMultiChunkProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import kport.modularmagic.common.integration.jei.ingredient.DemonWill;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class RequirementWillMultiChunk extends ComponentRequirement<DemonWill, RequirementTypeWillMultiChunk> implements IMultiChunkRequirement {
    public EnumDemonWillType willType;
    private final int chunkRange;
    private final double amount;
    private final double minPerChunk;
    private final double maxPerChunk;


    public RequirementWillMultiChunk(IOType actionType, int chunkRange, double amount, double minPerChunk, double maxPerChunk, EnumDemonWillType willType) {
        super((RequirementTypeWillMultiChunk) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_WILL_MULTI_CHUNK), actionType);
        this.chunkRange = chunkRange;
        this.amount = amount;
        this.minPerChunk = minPerChunk;
        this.maxPerChunk = maxPerChunk;
        this.willType = willType;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cpn = component.getComponent();
        return cpn.getContainerProvider() instanceof TileWillMultiChunkProvider &&
                cpn.getComponentType() instanceof ComponentWillMultiChunk &&
                cpn.ioType == getActionType();
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if (!canStartCrafting(component, context, Collections.emptyList()).isSuccess()) return false;

        if (getActionType() == IOType.INPUT) {
            TileWillMultiChunkProvider willProvider = getProviderFromComponent(component);
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> willProvider.handle(this, context.getMachineController().getPos(), true));
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if (getActionType() == IOType.OUTPUT) {
            TileWillMultiChunkProvider willProvider = getProviderFromComponent(component);
            ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> willProvider.handle(this, context.getMachineController().getPos(), true));
        }
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        TileWillMultiChunkProvider willProvider = getProviderFromComponent(component);

        if (!willProvider.canHandle(this, context.getMachineController().getPos())) {
            return CraftCheck.failure("cannot handle this component"); // todo fix:
        }
        return CraftCheck.success();
    }

    @Override
    public ComponentRequirement<DemonWill, RequirementTypeWillMultiChunk> deepCopy() {
        return new RequirementWillMultiChunk(this.actionType, this.chunkRange, this.amount, this.minPerChunk, this.maxPerChunk, this.willType);
    }

    @Override
    public ComponentRequirement<DemonWill, RequirementTypeWillMultiChunk> deepCopyModified(List<RecipeModifier> list) {
        return deepCopy();
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "";
    }

    @Override
    public JEIComponent<DemonWill> provideJEIComponent() {
        return new JEIComponentWill(this);
    }

    private TileWillMultiChunkProvider getProviderFromComponent(ProcessingComponent<?> component) {
        return (TileWillMultiChunkProvider) component.getComponent().getContainerProvider();
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
        return this.minPerChunk;
    }

    @Override
    public double getMaxPerChunk() {
        return this.maxPerChunk;
    }
}
