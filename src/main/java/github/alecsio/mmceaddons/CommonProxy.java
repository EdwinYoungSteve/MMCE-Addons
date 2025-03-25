package github.alecsio.mmceaddons;

import github.alecsio.mmceaddons.common.lib.ModularMachineryAddonsBlocks;
import github.alecsio.mmceaddons.common.registry.internal.EventHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;

/**
 * CommonProxy handles shared mod-side logic for the server and client.
 */
public class CommonProxy {
    public static CreativeTabs creativeTabModularMachineryAddons;

    private static boolean preInitCalled = false;

    public void preInit(FMLPreInitializationEvent event) {
        // Prevent duplicate calls
        if (preInitCalled) return;
        preInitCalled = true;

        ModularMachineryAddons.logger.info("CommonProxy: Running preInit");

        creativeTabModularMachineryAddons = new CreativeTabs(ModularMachineryAddons.MODID) {
            @Override
            @Nonnull
            public ItemStack createIcon() {
                return new ItemStack(ModularMachineryAddonsBlocks.blockRadiationProviderInput);
            }
        };

        // Register mod event handlers
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        // Register mod items, blocks, machines, and other assets
        ModularMachineryAddonsBlocks.initialise();
        //RegistryBlocks.initialise();
        //RegistryItems.initialise();
        //AddonRequirements.initRequirements();

        // Set up network handling (GUI, packets, etc.)
        //NetworkRegistry.INSTANCE.registerGuiHandler(ModularMachineryAddons.MODID, new GuiHandler());
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    // Optional methods to register models; these would be overridden on the client side
    public void registerItemModel(net.minecraft.item.Item item) {}

    public void registerBlockModel(net.minecraft.block.Block block) {}
}