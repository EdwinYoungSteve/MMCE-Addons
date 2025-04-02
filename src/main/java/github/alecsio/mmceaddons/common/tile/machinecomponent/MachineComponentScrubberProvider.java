package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.nuclearcraft.RequirementScrubber;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentScrubberProvider extends MachineComponent<IRequirementHandler<RequirementScrubber>> {

    private final IRequirementHandler<RequirementScrubber> tileScrubberProvider;

    public MachineComponentScrubberProvider(IOType ioType, IRequirementHandler<RequirementScrubber> tileScrubberProvider) {
        super(ioType);
        this.tileScrubberProvider = tileScrubberProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_SCRUBBER);
    }

    @Override
    public IRequirementHandler<RequirementScrubber> getContainerProvider() {
        return tileScrubberProvider;
    }
}
