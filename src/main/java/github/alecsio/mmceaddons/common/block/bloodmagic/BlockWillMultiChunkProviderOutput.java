
package github.alecsio.mmceaddons.common.block.bloodmagic;

import github.alecsio.mmceaddons.common.block.base.BaseBlockMachineComponent;
import github.alecsio.mmceaddons.common.tile.TileWillMultiChunkProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockWillMultiChunkProviderOutput extends BaseBlockMachineComponent {

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileWillMultiChunkProvider.Output();
    }
}
