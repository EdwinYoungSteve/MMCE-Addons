package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.registry.ModularMachineryAddonsComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementMeteor;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentMeteorProvider extends MachineComponent<IRequirementHandler<RequirementMeteor>> {

    private final IRequirementHandler<RequirementMeteor> tileMeteorProvider;

    public MachineComponentMeteorProvider(IOType ioType, IRequirementHandler<RequirementMeteor> tileMeteorProvider) {
        super(ioType);
        this.tileMeteorProvider = tileMeteorProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonsComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonsComponents.KEY_COMPONENT_METEOR);
    }

    @Override
    public IRequirementHandler<RequirementMeteor> getContainerProvider() {
        return tileMeteorProvider;
    }
}
