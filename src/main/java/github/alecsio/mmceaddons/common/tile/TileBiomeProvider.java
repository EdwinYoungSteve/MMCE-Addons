package github.alecsio.mmceaddons.common.tile;

import github.alecsio.mmceaddons.common.crafting.requirement.RequirementBiome;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentBiomeProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;

import javax.annotation.Nullable;

public class TileBiomeProvider extends TileColorableMachineComponent implements MachineComponentTile, IRequirementHandler<RequirementBiome> {

    @Override
    public CraftCheck canHandle(RequirementBiome requirement) {
        return this.world.getBiome(this.getPos()).getRegistryName().toString().equalsIgnoreCase(requirement.getBiome().getRegistryName()) ? CraftCheck.success() : CraftCheck.failure("lmfao") ;
    }

    @Override
    public void handle(RequirementBiome requirement) {
        // *eats the biome*
    }

    @Nullable
    @Override
    public MachineComponentBiomeProvider provideComponent() {
        return new MachineComponentBiomeProvider(IOType.INPUT, this);
    }
}
