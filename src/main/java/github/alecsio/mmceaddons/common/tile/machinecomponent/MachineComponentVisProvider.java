package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.thaumcraft.TileVisProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentVisProvider extends MachineComponent<TileVisProvider> {

    private final TileVisProvider tileVisProvider;

    public MachineComponentVisProvider(IOType ioType, TileVisProvider tileVisProvider) {
        super(ioType);
        this.tileVisProvider = tileVisProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_VIS);
    }

    @Override
    public TileVisProvider getContainerProvider() {
        return this.tileVisProvider;
    }
}
