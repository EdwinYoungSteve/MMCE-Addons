package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.RequirementRadiation;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentRadiationProvider extends MachineComponent<IRequirementHandler<RequirementRadiation>> {

    private final IRequirementHandler<RequirementRadiation> radiationProvider;

    public MachineComponentRadiationProvider(IOType ioType, IRequirementHandler<RequirementRadiation> radiationProvider) {
        super(ioType);
        this.radiationProvider = radiationProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_RADIATION);
    }

    @Override
    public IRequirementHandler<RequirementRadiation> getContainerProvider() {
        return radiationProvider;
    }
}
