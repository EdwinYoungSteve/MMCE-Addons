package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.RequirementRadiation;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class MachineComponentRadiationProvider extends BaseMachineComponent<RequirementRadiation> {

    public MachineComponentRadiationProvider(IOType ioType, IRequirementHandler<RequirementRadiation> radiationHandler) {
        super(ioType, radiationHandler);
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_RADIATION);
    }
}
