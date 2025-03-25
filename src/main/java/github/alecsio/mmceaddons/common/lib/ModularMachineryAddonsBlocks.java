package github.alecsio.mmceaddons.common.lib;


import github.alecsio.mmceaddons.common.block.BlockRadiationProviderInput;
import github.alecsio.mmceaddons.common.block.BlockRadiationProviderOutput;
import github.alecsio.mmceaddons.common.block.BlockWillMultiChunkProviderInput;
import github.alecsio.mmceaddons.common.block.BlockWillMultiChunkProviderOutput;

public class ModularMachineryAddonsBlocks {
    // Radiation
    public static BlockRadiationProviderInput blockRadiationProviderInput;
    public static BlockRadiationProviderOutput blockRadiationProviderOutput;

    // Will
    public static BlockWillMultiChunkProviderInput blockWillMultiChunkProviderInput;
    public static BlockWillMultiChunkProviderOutput blockWillMultiChunkProviderOutput;

    public static void initialise() {
        blockRadiationProviderInput = new BlockRadiationProviderInput();
        blockRadiationProviderOutput = new BlockRadiationProviderOutput();

        blockWillMultiChunkProviderInput = new BlockWillMultiChunkProviderInput();
        blockWillMultiChunkProviderOutput = new BlockWillMultiChunkProviderOutput();
    }
}
