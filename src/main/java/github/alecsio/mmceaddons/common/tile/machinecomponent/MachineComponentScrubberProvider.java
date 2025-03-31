package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.tile.nuclearcraft.TileScrubberProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentScrubberProvider extends MachineComponent<TileScrubberProvider> {

    private final TileScrubberProvider tileScrubberProvider;

    public MachineComponentScrubberProvider(IOType ioType, TileScrubberProvider tileScrubberProvider) {
        super(ioType);
        this.tileScrubberProvider = tileScrubberProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_SCRUBBER);
    }

    @Override
    public TileScrubberProvider getContainerProvider() {
        return tileScrubberProvider;
    }
}
