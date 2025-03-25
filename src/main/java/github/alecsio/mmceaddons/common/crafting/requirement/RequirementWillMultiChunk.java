package github.alecsio.mmceaddons.common.crafting.requirement;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import github.alecsio.mmceaddons.common.crafting.requirement.types.ModularMachineryAddonsRequirements;
import github.alecsio.mmceaddons.common.crafting.requirement.types.RequirementTypeWillMultiChunk;
import github.alecsio.mmceaddons.common.tile.TileWillMultiChunkProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import kport.modularmagic.common.crafting.component.ComponentWill;
import kport.modularmagic.common.integration.jei.ingredient.DemonWill;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class RequirementWillMultiChunk extends AbstractMultiChunkRequirement<DemonWill, RequirementTypeWillMultiChunk> {
    public EnumDemonWillType willType;

    public RequirementWillMultiChunk(IOType actionType, int chunkRange, double amount, double minPerChunk, double maxPerChunk, EnumDemonWillType willType) {
        super((RequirementTypeWillMultiChunk) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsRequirements.KEY_REQUIREMENT_WILL_MULTI_CHUNK), actionType, chunkRange, amount, minPerChunk, maxPerChunk);
        this.willType = willType;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent<?> cpn = component.getComponent();
        return cpn.getContainerProvider() instanceof TileWillMultiChunkProvider &&
                cpn.getComponentType() instanceof ComponentWill &&
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
        return null;
    }

    @Override
    public ComponentRequirement<DemonWill, RequirementTypeWillMultiChunk> deepCopyModified(List<RecipeModifier> list) {
        return null;
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "";
    }

    @Override
    public JEIComponent<DemonWill> provideJEIComponent() {
        return null;
    }

    private TileWillMultiChunkProvider getProviderFromComponent(ProcessingComponent<?> component) {
        return (TileWillMultiChunkProvider) component.getComponent().getContainerProvider();
    }
}
