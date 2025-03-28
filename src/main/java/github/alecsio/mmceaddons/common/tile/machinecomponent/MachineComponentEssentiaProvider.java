package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.handler.IEssentiaHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentEssentiaProvider extends MachineComponent<IEssentiaHandler> {
    private final IEssentiaHandler essentiaHandler;

    public MachineComponentEssentiaProvider(IOType ioType, IEssentiaHandler essentiaHandler) {
        super(ioType);
        this.essentiaHandler = essentiaHandler;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_ESSENTIA);
    }

    @Override
    public IEssentiaHandler getContainerProvider() {
        return this.essentiaHandler;
    }
}
