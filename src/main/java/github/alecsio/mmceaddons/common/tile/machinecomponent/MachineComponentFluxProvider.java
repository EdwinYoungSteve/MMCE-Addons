package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.thaumcraft.TileFluxProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentFluxProvider extends MachineComponent<TileFluxProvider> {
    private final TileFluxProvider fluxProvider;

    public MachineComponentFluxProvider(TileFluxProvider fluxProvider, IOType ioType) {
        super(ioType);
        this.fluxProvider = fluxProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_FLUX);
    }

    @Override
    public TileFluxProvider getContainerProvider() {
        return fluxProvider;
    }
}
