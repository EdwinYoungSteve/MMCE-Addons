package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.RequirementBiome;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentBiomeProvider extends MachineComponent<IRequirementHandler<RequirementBiome>> {

    private final IRequirementHandler<RequirementBiome> biomeHandler;

    public MachineComponentBiomeProvider(IOType ioType, IRequirementHandler<RequirementBiome> biomeHandler) {
        super(ioType);
        this.biomeHandler = biomeHandler;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_BIOME);
    }

    @Override
    public IRequirementHandler<RequirementBiome> getContainerProvider() {
        return biomeHandler;
    }
}
