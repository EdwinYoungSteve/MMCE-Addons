package github.alecsio.mmceaddons.common.lib;


import github.alecsio.mmceaddons.common.block.ae2.BlockMEEssentiaInputBus;
import github.alecsio.mmceaddons.common.block.ae2.BlockMEEssentiaOutputBus;
import github.alecsio.mmceaddons.common.block.bloodmagic.BlockMeteorProviderOutput;
import github.alecsio.mmceaddons.common.block.bloodmagic.BlockWillMultiChunkProviderInput;
import github.alecsio.mmceaddons.common.block.bloodmagic.BlockWillMultiChunkProviderOutput;
import github.alecsio.mmceaddons.common.block.nuclearcraft.BlockRadiationProviderInput;
import github.alecsio.mmceaddons.common.block.nuclearcraft.BlockRadiationProviderOutput;
import github.alecsio.mmceaddons.common.block.nuclearcraft.scrubber.BlockScrubberProviderInput;
import github.alecsio.mmceaddons.common.block.thaumcraft.BlockFluxProviderInput;
import github.alecsio.mmceaddons.common.block.thaumcraft.BlockFluxProviderOutput;
import github.alecsio.mmceaddons.common.block.thaumcraft.BlockVisProviderInput;
import github.alecsio.mmceaddons.common.block.thaumcraft.BlockVisProviderOutput;

public class ModularMachineryAddonsBlocks {
    // Radiation
    public static BlockRadiationProviderInput blockRadiationProviderInput;
    public static BlockRadiationProviderOutput blockRadiationProviderOutput;
    public static BlockScrubberProviderInput blockScrubberProviderInput;

    // Will
    public static BlockWillMultiChunkProviderInput blockWillMultiChunkProviderInput;
    public static BlockWillMultiChunkProviderOutput blockWillMultiChunkProviderOutput;

    // Meteor
    public static BlockMeteorProviderOutput blockMeteorProviderOutput;

    // Thaumic Energistics
    public static BlockMEEssentiaInputBus blockMEEssentiaInputBus;
    public static BlockMEEssentiaOutputBus blockMEEssentiaOutputBus;

    // Thaumcraft
    public static BlockFluxProviderInput blockFluxProviderInput;
    public static BlockFluxProviderOutput blockFluxProviderOutput;

    public static BlockVisProviderInput blockVisProviderInput;
    public static BlockVisProviderOutput blockVisProviderOutput;

    public static void initialise() {
        blockRadiationProviderInput = new BlockRadiationProviderInput();
        blockRadiationProviderOutput = new BlockRadiationProviderOutput();

        blockWillMultiChunkProviderInput = new BlockWillMultiChunkProviderInput();
        blockWillMultiChunkProviderOutput = new BlockWillMultiChunkProviderOutput();

        blockMeteorProviderOutput = new BlockMeteorProviderOutput();

        blockMEEssentiaInputBus = new BlockMEEssentiaInputBus();
        blockMEEssentiaOutputBus = new BlockMEEssentiaOutputBus();

        blockFluxProviderInput = new BlockFluxProviderInput();
        blockFluxProviderOutput = new BlockFluxProviderOutput();

        blockVisProviderInput = new BlockVisProviderInput();
        blockVisProviderOutput = new BlockVisProviderOutput();

        blockScrubberProviderInput = new BlockScrubberProviderInput();
    }
}
