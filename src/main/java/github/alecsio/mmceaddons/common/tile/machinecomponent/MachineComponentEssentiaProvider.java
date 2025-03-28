package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import thaumicenergistics.api.EssentiaStack;

public class MachineComponentEssentiaProvider extends MachineComponent<IRequirementHandler<EssentiaStack>> {
    private final IRequirementHandler<EssentiaStack> essentiaHandler;

    public MachineComponentEssentiaProvider(IOType ioType, IRequirementHandler<EssentiaStack> essentiaHandler) {
        super(ioType);
        this.essentiaHandler = essentiaHandler;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_ESSENTIA);
    }

    @Override
    public IRequirementHandler<EssentiaStack> getContainerProvider() {
        return this.essentiaHandler;
    }
}
