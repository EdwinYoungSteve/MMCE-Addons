package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.thaumcraft.RequirementVis;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentVisProvider extends MachineComponent<IRequirementHandler<RequirementVis>> {

    private final IRequirementHandler<RequirementVis> tileVisProvider;

    public MachineComponentVisProvider(IOType ioType, IRequirementHandler<RequirementVis> tileVisProvider) {
        super(ioType);
        this.tileVisProvider = tileVisProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_VIS);
    }

    @Override
    public IRequirementHandler<RequirementVis> getContainerProvider() {
        return this.tileVisProvider;
    }
}
