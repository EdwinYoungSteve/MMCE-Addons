package github.alecsio.mmceaddons.common.tile.bloodmagic;

import WayofTime.bloodmagic.entity.projectile.EntityMeteor;
import WayofTime.bloodmagic.meteor.Meteor;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import github.alecsio.mmceaddons.common.tile.machinecomponent.MachineComponentMeteorProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class TileMeteorProvider extends TileColorableMachineComponent implements MachineComponentTile, IRequirementHandler<Meteor> {

    public static class Output extends TileMeteorProvider {

        private EntityMeteor currentMeteor;

        @Nullable
        @Override
        public MachineComponentMeteorProvider provideComponent() {
            return new MachineComponentMeteorProvider(IOType.OUTPUT, this);
        }

        @Override
        public CraftCheck canHandle(Meteor meteor) {
            if (currentMeteor != null && !currentMeteor.isDead) {return CraftCheck.failure("Meteor is still alive");}
            int radius = (int) (double) meteor.getRadius();
            double floatingRadius = meteor.getRadius();
            BlockPos startingPos = pos.add(0, meteor.getRadius() + 1, 0);

            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = -radius; k <= radius; k++) {
                        if (i * i + j * j + k * k > (floatingRadius + 0.5) * (floatingRadius + 0.5)) {
                            continue; // Outside the sphere, skip this block
                        }

                        BlockPos newPos = startingPos.add(i, j, k);

                        // Check if the block is not air
                        if (!world.isAirBlock(newPos) && !world.getBlockState(newPos).getBlock().equals(Blocks.OBSIDIAN)) {
                            return CraftCheck.failure("lol"); // Found a non-air block, sphere is not empty
                        }
                    }
                }
            }
            return CraftCheck.success(); // All blocks in the sphere are air
        }

        @Override
        public void handle(Meteor meteor) {
            this.currentMeteor = new EntityMeteor(world, pos.getX(), 260, pos.getZ(), 0, -0.1, 0, 1, 0, 0.2);
            this.currentMeteor.setMeteorStack(meteor.getCatalystStack());
            world.spawnEntity(this.currentMeteor);
        }
    }
}
