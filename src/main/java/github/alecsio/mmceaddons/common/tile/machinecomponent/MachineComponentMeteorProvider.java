package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.TileMeteorProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentMeteorProvider extends MachineComponent<TileMeteorProvider> {
    private final TileMeteorProvider tileMeteorProvider;

    public MachineComponentMeteorProvider(IOType ioType, TileMeteorProvider tileMeteorProvider) {
        super(ioType);
        this.tileMeteorProvider = tileMeteorProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_METEOR);
    }

    @Override
    public TileMeteorProvider getContainerProvider() {
        return tileMeteorProvider;
    }
}
