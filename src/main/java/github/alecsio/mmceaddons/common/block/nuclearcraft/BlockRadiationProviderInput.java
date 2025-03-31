package github.alecsio.mmceaddons.common.block.nuclearcraft;

import github.alecsio.mmceaddons.common.block.base.BaseBlockMachineComponent;
import github.alecsio.mmceaddons.common.tile.nuclearcraft.TileRadiationProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRadiationProviderInput extends BaseBlockMachineComponent {

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileRadiationProvider.Input();
    }
}
