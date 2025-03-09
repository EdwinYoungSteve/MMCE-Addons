/*******************************************************************************
 * HellFirePvP / Modular Machinery 2019
 *
 * This project is licensed under GNU GENERAL PUBLIC LICENSE Version 3.
 * The source code is available on github: https://github.com/HellFirePvP/ModularMachinery
 * For further details, see the License file there.
 ******************************************************************************/

package github.alecsio.mmceaddons.client;

import github.alecsio.mmceaddons.CommonProxy;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is part of the Modular Machinery Mod
 * The complete source code for this mod can be found on github.
 * Class: ClientProxy
 * Created by HellFirePvP
 * Date: 26.06.2017 / 21:01
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ModularMachineryAddons.MODID)
public class ClientProxy extends CommonProxy {
    private static final List<Item> ITEM_MODELS_TO_REGISTER = new ArrayList<>();
    private static final List<Block> BLOCK_MODELS_TO_REGISTER = new ArrayList<>();

    @SubscribeEvent
    public void onModelRegister(ModelRegistryEvent event) {
        registerModels();
    }

    @Override
    public void registerItemModel(Item item) {
        ITEM_MODELS_TO_REGISTER.add(item);
    }

    @Override
    public void registerBlockModel(Block block) {
        BLOCK_MODELS_TO_REGISTER.add(block);
    }

    private void registerModels() {
        registerItemModels();
        registerBlockModels();
    }

    private void registerBlockModels() {
        BLOCK_MODELS_TO_REGISTER.forEach(block -> {
            ModularMachineryAddons.logger.info("Registering block model for " + block.getRegistryName());
            Item item = Item.getItemFromBlock(block);
            ModelBakery.registerItemVariants(item, block.getRegistryName());
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        });
    }

    private void registerItemModels() {
        ITEM_MODELS_TO_REGISTER.forEach(item -> {
            ModularMachineryAddons.logger.info("Registering item model for " + item.getRegistryName());
            ModelBakery.registerItemVariants(item, item.getRegistryName());
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        });
    }


}
