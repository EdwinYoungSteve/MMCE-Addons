package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementWillMultiChunk;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentWillMultiChunkProvider extends MachineComponent<IRequirementHandler<RequirementWillMultiChunk>> {

    private final IRequirementHandler<RequirementWillMultiChunk> tileWillMultiChunkProvider;

    public MachineComponentWillMultiChunkProvider(IOType ioType, IRequirementHandler<RequirementWillMultiChunk> tileWillMultiChunkProvider) {
        super(ioType);
        this.tileWillMultiChunkProvider = tileWillMultiChunkProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_WILL);
    }

    @Override
    public IRequirementHandler<RequirementWillMultiChunk> getContainerProvider() {
        return tileWillMultiChunkProvider;
    }
}
