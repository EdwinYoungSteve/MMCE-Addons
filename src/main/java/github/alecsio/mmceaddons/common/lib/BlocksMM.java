/*******************************************************************************
 * HellFirePvP / Modular Machinery 2019
 *
 * This project is licensed under GNU GENERAL PUBLIC LICENSE Version 3.
 * The source code is available on github: https://github.com/HellFirePvP/ModularMachinery
 * For further details, see the License file there.
 ******************************************************************************/

package github.alecsio.mmceaddons.common.lib;


import github.alecsio.mmceaddons.common.block.BlockRadiationProviderInput;
import github.alecsio.mmceaddons.common.block.BlockRadiationProviderOutput;

/**
 * This class is part of the Modular Machinery Mod
 * The complete source code for this mod can be found on github.
 * Class: BlocksMM
 * Created by HellFirePvP
 * Date: 28.06.2017 / 20:22
 */
public class BlocksMM {

    public static BlockRadiationProviderInput blockRadiationProviderInput;

    public static BlockRadiationProviderOutput blockRadiationProviderOutput;

    public static void initialise() {
        blockRadiationProviderInput = new BlockRadiationProviderInput();
        blockRadiationProviderOutput = new BlockRadiationProviderOutput();
    }
}
