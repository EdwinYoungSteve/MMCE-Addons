package github.alecsio.mmceaddons.common.tile.thaumcraft;

import appeng.api.config.Actionable;
import appeng.api.storage.IMEInventory;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import github.alecsio.mmceaddons.common.lib.ModularMachineryAddonsBlocks;
import github.alecsio.mmceaddons.common.tile.ae2.MEEssentiaBus;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentEssentiaProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import net.minecraft.item.ItemStack;
import thaumicenergistics.api.EssentiaStack;
import thaumicenergistics.api.storage.IAEEssentiaStack;
import thaumicenergistics.integration.appeng.AEEssentiaStack;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MEEssentiaInputBus extends MEEssentiaBus {

    private static final Lock lock = new ReentrantLock();

    @Override
    public ItemStack getVisualItemStack() {
        return new ItemStack(ModularMachineryAddonsBlocks.blockMEEssentiaInputBus);
    }

    @Nullable
    @Override
    public MachineComponent<IRequirementHandler<RequirementEssentia>> provideComponent() {
        return new MachineComponentEssentiaProvider(IOType.INPUT, this);
    }

    @Override
    protected boolean canPerformOperation(Actionable actionable, EssentiaStack essentia) {
        lock.lock();
        try {
            Optional<IMEInventory<IAEEssentiaStack>> optInventory = getStorageInventory();
            if (!optInventory.isPresent()) {return false;}

            IAEEssentiaStack imported = optInventory.get().extractItems(AEEssentiaStack.fromEssentiaStack(essentia), actionable, this.source);
            return imported != null && imported.getStackSize() >= essentia.getAmount();
        } finally {
            lock.unlock();
        }
    }
}
