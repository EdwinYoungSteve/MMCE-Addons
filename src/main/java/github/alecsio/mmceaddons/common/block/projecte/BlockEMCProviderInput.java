package github.alecsio.mmceaddons.common.block.projecte;

import github.alecsio.mmceaddons.common.block.base.BaseBlockMachineComponent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockEMCProviderInput extends BaseBlockMachineComponent {

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return super.createTileEntity(world, state);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return super.createNewTileEntity(worldIn, meta);
    }
}
