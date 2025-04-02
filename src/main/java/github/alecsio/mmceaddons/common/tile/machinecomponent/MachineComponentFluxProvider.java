package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementFlux;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentFluxProvider extends MachineComponent<IRequirementHandler<RequirementFlux>> {

    private final IRequirementHandler<RequirementFlux> fluxProvider;

    public MachineComponentFluxProvider(IRequirementHandler<RequirementFlux> fluxProvider, IOType ioType) {
        super(ioType);
        this.fluxProvider = fluxProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_FLUX);
    }

    @Override
    public IRequirementHandler<RequirementFlux> getContainerProvider() {
        return fluxProvider;
    }
}
