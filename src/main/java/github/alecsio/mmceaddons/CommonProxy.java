package github.alecsio.mmceaddons;

import github.alecsio.mmceaddons.common.lib.BlocksMM;
import github.alecsio.mmceaddons.common.registry.internal.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * CommonProxy handles shared mod-side logic for the server and client.
 */
public class CommonProxy {

    private static boolean preInitCalled = false;
    private static boolean initCalled = false;
    private static boolean postInitCalled = false;

    public void preInit(FMLPreInitializationEvent event) {
        // Prevent duplicate calls
        if (preInitCalled) return;
        preInitCalled = true;

        ModularMachineryAddons.logger.info("CommonProxy: Running preInit");

        // Register mod event handlers
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        // Register mod items, blocks, machines, and other assets
        BlocksMM.initialise();
        //RegistryBlocks.initialise();
        //RegistryItems.initialise();
        //AddonRequirements.initRequirements();

        // Set up network handling (GUI, packets, etc.)
        //NetworkRegistry.INSTANCE.registerGuiHandler(ModularMachineryAddons.MODID, new GuiHandler());
    }

    public void init(FMLInitializationEvent event) {
        // Prevent duplicate calls
        if (initCalled) return;
        initCalled = true;

        ModularMachineryAddons.logger.info("CommonProxy: Running init");

        // Initialize things such as recipes, integrations, or any other mid-phase logic
    }

    public void postInit(FMLPostInitializationEvent event) {
        // Prevent duplicate calls
        if (postInitCalled) return;
        postInitCalled = true;

        ModularMachineryAddons.logger.info("CommonProxy: Running postInit");

        // Handle mods integration or additional finalization logic here
    }

    // Optional methods to register models; these would be overridden on the client side
    public void registerItemModel(net.minecraft.item.Item item) {
        // CommonProxy does not handle models; ClientProxy will override this
    }

    public void registerBlockModel(net.minecraft.block.Block block) {
        // CommonProxy does not handle models; ClientProxy will override this
    }
}