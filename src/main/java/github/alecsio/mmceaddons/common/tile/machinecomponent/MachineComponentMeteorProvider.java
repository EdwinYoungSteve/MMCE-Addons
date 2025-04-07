package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.helper.MeteorProviderCopy;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementMeteor;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.base.BaseMachineComponent;
import github.alecsio.mmceaddons.common.tile.machinecomponent.base.BaseMachineComponentCopy;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class MachineComponentMeteorProvider extends BaseMachineComponentCopy<RequirementMeteor> {

    public MachineComponentMeteorProvider(IOType ioType, IRequirementHandler<RequirementMeteor> meteorHandler) {
        super(ioType, new MeteorProviderCopy(meteorHandler));
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_METEOR);
    }
}
