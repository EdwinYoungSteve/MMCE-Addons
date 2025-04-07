package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.helper.BaseProviderCopy;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementBiome;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.base.BaseMachineComponent;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class MachineComponentBiomeProvider extends BaseMachineComponent<RequirementBiome> {

    public MachineComponentBiomeProvider(IOType ioType, IRequirementHandler<RequirementBiome> biomeHandler) {
        super(ioType, biomeHandler);
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_BIOME);
    }
}
