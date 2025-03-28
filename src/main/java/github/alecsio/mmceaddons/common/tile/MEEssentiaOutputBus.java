package github.alecsio.mmceaddons.common.tile;

import appeng.api.config.Actionable;
import github.alecsio.mmceaddons.common.lib.ModularMachineryAddonsBlocks;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentEssentiaProvider;
import github.alecsio.mmceaddons.common.tile.machinecomponent.ae2.MEEssentiaBus;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import net.minecraft.item.ItemStack;
import thaumicenergistics.api.EssentiaStack;
import thaumicenergistics.integration.appeng.AEEssentiaStack;

import javax.annotation.Nullable;

public class MEEssentiaOutputBus extends MEEssentiaBus {

    @Override
    public ItemStack getVisualItemStack() {
        return new ItemStack(ModularMachineryAddonsBlocks.blockMEEssentiaOutputBus);
    }

    @Nullable
    @Override
    public MachineComponent<IRequirementHandler<EssentiaStack>> provideComponent() {
        return new MachineComponentEssentiaProvider(IOType.OUTPUT, this);
    }

    @Override
    protected boolean canPerformOperation(Actionable actionable, EssentiaStack essentia) {
        return getStorageInventory().injectItems(AEEssentiaStack.fromEssentiaStack(essentia), actionable, this.source) == null;
    }
}
