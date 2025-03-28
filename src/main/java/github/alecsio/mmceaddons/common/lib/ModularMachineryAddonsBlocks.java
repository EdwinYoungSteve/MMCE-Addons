package github.alecsio.mmceaddons.common.lib;


import github.alecsio.mmceaddons.common.block.*;
import github.alecsio.mmceaddons.common.block.ae2.BlockMEEssentiaInputBus;
import github.alecsio.mmceaddons.common.block.ae2.BlockMEEssentiaOutputBus;

public class ModularMachineryAddonsBlocks {
    // Radiation
    public static BlockRadiationProviderInput blockRadiationProviderInput;
    public static BlockRadiationProviderOutput blockRadiationProviderOutput;

    // Will
    public static BlockWillMultiChunkProviderInput blockWillMultiChunkProviderInput;
    public static BlockWillMultiChunkProviderOutput blockWillMultiChunkProviderOutput;

    // Meteor
    public static BlockMeteorProviderOutput blockMeteorProviderOutput;

    // Thaum
    public static BlockMEEssentiaInputBus blockMEEssentiaInputBus;
    public static BlockMEEssentiaOutputBus blockMEEssentiaOutputBus;

    public static void initialise() {
        blockRadiationProviderInput = new BlockRadiationProviderInput();
        blockRadiationProviderOutput = new BlockRadiationProviderOutput();

        blockWillMultiChunkProviderInput = new BlockWillMultiChunkProviderInput();
        blockWillMultiChunkProviderOutput = new BlockWillMultiChunkProviderOutput();

        blockMeteorProviderOutput = new BlockMeteorProviderOutput();

        blockMEEssentiaInputBus = new BlockMEEssentiaInputBus();
        blockMEEssentiaOutputBus = new BlockMEEssentiaOutputBus();
    }
}
