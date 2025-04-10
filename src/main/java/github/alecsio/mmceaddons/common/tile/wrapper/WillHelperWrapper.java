package github.alecsio.mmceaddons.common.tile.wrapper;

import WayofTime.bloodmagic.demonAura.WorldDemonWillHandler;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static github.alecsio.mmceaddons.common.tile.wrapper.BaseHelperWrapper.withLock;

public class WillHelperWrapper {

    private static final Lock lock = new ReentrantLock();

    public static double getWill(World world, BlockPos pos, EnumDemonWillType willType) {
        return withLock(lock, () -> WorldDemonWillHandler.getCurrentWill(world, pos, willType));
    }

    public static void drainWill(World world, BlockPos pos, EnumDemonWillType willType, double amount) {
        withLock(lock, () -> WorldDemonWillHandler.drainWill(world, pos, willType, amount, true));
    }

    public static void addWill(World world, BlockPos pos, EnumDemonWillType willType, double amount) {
        withLock(lock, () -> WorldDemonWillHandler.fillWill(world, pos, willType, amount, true));
    }
}
