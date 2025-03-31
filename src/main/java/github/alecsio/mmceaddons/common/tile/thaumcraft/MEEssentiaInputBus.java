package github.alecsio.mmceaddons.common.tile.thaumcraft;

import appeng.api.config.Actionable;
import github.alecsio.mmceaddons.common.lib.ModularMachineryAddonsBlocks;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentEssentiaProvider;
import github.alecsio.mmceaddons.common.tile.machinecomponent.ae2.MEEssentiaBus;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import net.minecraft.item.ItemStack;
import thaumicenergistics.api.EssentiaStack;
import thaumicenergistics.api.storage.IAEEssentiaStack;
import thaumicenergistics.integration.appeng.AEEssentiaStack;

import javax.annotation.Nullable;

public class MEEssentiaInputBus extends MEEssentiaBus {

    @Override
    public ItemStack getVisualItemStack() {
        return new ItemStack(ModularMachineryAddonsBlocks.blockMEEssentiaInputBus);
    }

    @Nullable
    @Override
    public MachineComponent<IRequirementHandler<EssentiaStack>> provideComponent() {
        return new MachineComponentEssentiaProvider(IOType.INPUT, this);
    }

    @Override
    protected boolean canPerformOperation(Actionable actionable, EssentiaStack essentia) {
        IAEEssentiaStack imported = getStorageInventory().extractItems(AEEssentiaStack.fromEssentiaStack(essentia), actionable, this.source);
        return imported == null || imported.getStackSize() >= essentia.getAmount();
    }
}
