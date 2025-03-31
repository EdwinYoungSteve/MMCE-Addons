package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.bloodmagic.TileWillMultiChunkProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentWillMultiChunkProvider extends MachineComponent<TileWillMultiChunkProvider> {

    private final TileWillMultiChunkProvider tileWillMultiChunkProvider;

    public MachineComponentWillMultiChunkProvider(IOType ioType, TileWillMultiChunkProvider tileWillMultiChunkProvider) {
        super(ioType);
        this.tileWillMultiChunkProvider = tileWillMultiChunkProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_WILL);
    }

    @Override
    public TileWillMultiChunkProvider getContainerProvider() {
        return tileWillMultiChunkProvider;
    }
}
