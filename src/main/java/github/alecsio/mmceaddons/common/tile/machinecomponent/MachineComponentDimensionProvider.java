package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementDimension;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentDimensionProvider extends MachineComponent<IRequirementHandler<RequirementDimension>> {

    private final IRequirementHandler<RequirementDimension> dimensionHandler;

    public MachineComponentDimensionProvider(IOType ioType, IRequirementHandler<RequirementDimension> dimensionHandler) {
        super(ioType);
        this.dimensionHandler = dimensionHandler;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_DIMENSION);
    }

    @Override
    public IRequirementHandler<RequirementDimension> getContainerProvider() {
        return this.dimensionHandler;
    }
}
