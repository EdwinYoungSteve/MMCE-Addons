package github.alecsio.mmceaddons.common.registry;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.item.ItemAdvancedConstructTool;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.item.ItemDynamicColor;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RegistryItems {
    public static final List<Item> ITEM_BLOCKS = new ArrayList<>();
    protected static final List<Item> ITEM_MODEL_REGISTER = new ArrayList<>();

    public static ItemAdvancedConstructTool constructTool;

    public static void initialise() {

        constructTool = new ItemAdvancedConstructTool();

        constructTool.setRegistryName(ModularMachineryAddons.MODID, "advancedconstructtool").setTranslationKey(ModularMachineryAddons.MODID + "." + constructTool.getClass().getSimpleName().toLowerCase());
        ITEM_BLOCKS.add(constructTool);

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

