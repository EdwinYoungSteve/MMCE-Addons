package github.alecsio.mmceaddons.common.tile;

import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentRadiationProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import nc.capability.radiation.source.IRadiationSource;
import nc.radiation.RadiationHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class TileRadiationProvider extends TileColorableMachineComponent implements MachineComponentTile {

    public void addRadiation(double amount) {
        RadiationHelper.addToSourceBuffer(RadiationHelper.getRadiationSource(world.getChunk(pos)), amount);
    }

    public void removeRadiation(double amount) {
        getSurroundingChunks(world, pos).stream()
                .filter(Chunk::isLoaded)
                .forEach(chunk -> {
                    IRadiationSource radiationSource = RadiationHelper.getRadiationSource(chunk);
                    radiationSource.setRadiationLevel(-Float.MAX_VALUE);
                    radiationSource.setRadiationBuffer(-Float.MAX_VALUE);
                });
    }

    public double getRadiation() {
        return getSurroundingChunks(world, pos).stream()
                .map(chunk -> Optional.ofNullable(RadiationHelper.getRadiationSource(chunk)))
                .filter(Optional::isPresent)
                .map(Optional::get).mapToDouble(IRadiationSource::getRadiationBuffer).sum();
    }

    public static class Input extends TileRadiationProvider {
        @Nullable
        @Override
        public MachineComponentRadiationProvider provideComponent() {
            return new MachineComponentRadiationProvider(IOType.INPUT, this);
        }
    }

    public static class Output extends TileRadiationProvider {
        @Nullable
        @Override
        public MachineComponentRadiationProvider provideComponent() {
            return new MachineComponentRadiationProvider(IOType.OUTPUT, this);
        }
    }

    public static List<Chunk> getSurroundingChunks(World world, BlockPos pos) {
        List<Chunk> chunks = new ArrayList<>();

        // Calculate the chunk coordinates of the position `pos`
        int chunkX = pos.getX() >> 4; // Divide by 16 (right shift by 4 bits)
        int chunkZ = pos.getZ() >> 4;

        // Iterate through the 3x3 surrounding chunks
        for (int x = chunkX - 1; x <= chunkX + 1; x++) {
            for (int z = chunkZ - 1; z <= chunkZ + 1; z++) {
                // Get the chunk at the current coordinates
                Chunk chunk = world.getChunk(x, z);
                chunks.add(chunk);
            }
        }

        return chunks;
    }


}
