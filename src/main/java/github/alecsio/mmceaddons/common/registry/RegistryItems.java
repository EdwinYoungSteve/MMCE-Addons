package github.alecsio.mmceaddons.common.registry;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RegistryItems {
    public static final List<Item> ITEM_BLOCKS = new ArrayList<>();
    protected static final List<Item> ITEM_MODEL_REGISTER = new ArrayList<>();

    public static void initialise() {
        registerItemBlocks();
        registerItemModels();
    }

    public void registerItemBlock(Item item) {
        ITEM_BLOCKS.add(item);
    }

    private static void registerItemBlocks() {
        ITEM_BLOCKS.forEach(RegistryItems::register);
    }

    private static <T extends Item> void register(T item) {
        ITEM_MODEL_REGISTER.add(item);
    }

    private static void registerItemModels() {
        ITEM_MODEL_REGISTER.forEach(ModularMachineryAddons.proxy::registerItemModel);
    }
}

