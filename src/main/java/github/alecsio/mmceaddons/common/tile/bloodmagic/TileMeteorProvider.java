package github.alecsio.mmceaddons.common.tile.bloodmagic;

import WayofTime.bloodmagic.entity.projectile.EntityMeteor;
import WayofTime.bloodmagic.meteor.Meteor;
import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementMeteor;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentMeteorProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class TileMeteorProvider extends TileColorableMachineComponent implements MachineComponentTile, IRequirementHandler<RequirementMeteor> {

    private static final int MAX_HEIGHT = 256;
    private static final int MAX_ALLOWED_BLOCKS_BETWEEN_TILE_AND_SKY = 1;

    public static class Output extends TileMeteorProvider {

        private EntityMeteor currentMeteor;
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        public AtomicBoolean reserved = new AtomicBoolean(false);

        @Nullable
        @Override
        public MachineComponentMeteorProvider provideComponent() {
            return new MachineComponentMeteorProvider(IOType.OUTPUT, this);
        }

        @Override
        public CraftCheck canHandle(RequirementMeteor requirement) {
            lock.readLock().lock();
            try {
                if (currentMeteor != null && !currentMeteor.isDead) {
                    return CraftCheck.failure("error.modularmachineryaddons.requirement.missing.meteor.alive");
                }


                BlockPos pos = this.getPos();
                if (!world.canSeeSky(pos)) {
                    int obstructedBlocks = 0;
                    for (int y = pos.getY() + 1; y <= MAX_HEIGHT; y++) {
                        if (!world.isAirBlock(pos.up(y - pos.getY()))) {
                            obstructedBlocks++;
                            if (obstructedBlocks > MAX_ALLOWED_BLOCKS_BETWEEN_TILE_AND_SKY) {
                                return CraftCheck.failure("error.modularmachineryaddons.requirement.missing.meteor");
                            }
                        }
                    }
                }

                return CraftCheck.success();
            } finally {
                lock.readLock().unlock();
            }
        }

        @Override
        public void handle(RequirementMeteor requirement) {
            lock.writeLock().lock();
            try {
                Meteor meteor = requirement.getMeteor();
                this.currentMeteor = new EntityMeteor(world, pos.getX(), 260, pos.getZ(), 0, -0.1, 0, 1, 0, 0.2);
                this.currentMeteor.setMeteorStack(meteor.getCatalystStack());
                world.spawnEntity(this.currentMeteor);
                markNoUpdateSync();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
