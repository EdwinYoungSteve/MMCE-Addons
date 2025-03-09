package github.alecsio.mmceaddons.common.tile.machinecomponent;

import github.alecsio.mmceaddons.common.crafting.component.ModularMachineryAddonComponents;
import github.alecsio.mmceaddons.common.tile.TileRadiationProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
public class MachineComponentRadiationProvider extends MachineComponent<TileRadiationProvider> {

    private final TileRadiationProvider radiationProvider;
    public MachineComponentRadiationProvider(IOType ioType, TileRadiationProvider radiationProvider) {
        super(ioType);
        this.radiationProvider = radiationProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ModularMachineryAddonComponents.COMPONENT_TYPE_REGISTRY.getValue(ModularMachineryAddonComponents.KEY_COMPONENT_RADIATION);
    }

    @Override
    public TileRadiationProvider getContainerProvider() {
        return radiationProvider;
    }
}
