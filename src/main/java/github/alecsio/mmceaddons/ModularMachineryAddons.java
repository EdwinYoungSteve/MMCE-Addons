package github.alecsio.mmceaddons;

import github.alecsio.mmceaddons.client.ClientTickHandler;
import github.alecsio.mmceaddons.client.MouseScrollHandler;
import github.alecsio.mmceaddons.common.assembly.handler.MachineAssemblyEventHandler;
import github.alecsio.mmceaddons.common.commands.CommandGetCacheInfo;
import github.alecsio.mmceaddons.common.item.handler.RightClickHandler;
import github.alecsio.mmceaddons.common.network.MouseScrollMessage;
import github.alecsio.mmceaddons.common.network.handler.MouseScrollMessageHandler;
import github.alecsio.mmceaddons.common.registry.RegistryItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = ModularMachineryAddons.MODID,
        name = ModularMachineryAddons.NAME,
        version = ModularMachineryAddons.VERSION,
        dependencies = "required-after:forge@[14.21.0.2371,);"
                + "required-after:modularmachinery@[2.1.5,);"
                + "after:jei@[4.13.1.222,);"
                + "after:bloodmagic@[0.0.0,);"
                + "after:thaumcraft@[0.0.0,);"
                + "after:thaumicenergistics@[0.0.0,);"
                + "after:nuclearcraft@[0.0.0,);"
        ,
        acceptedMinecraftVersions = "[1.12]",
        acceptableRemoteVersions = "[1.1.1]"
)
public class ModularMachineryAddons {

    public static final String MODID = "modularmachineryaddons";
    public static final String NAME = "Modular Machinery: Community Edition Addons";
    public static final String VERSION = "1.1.1";
    public static final String CLIENT_PROXY = "github.alecsio.mmceaddons.client.ClientProxy";
    public static final String COMMON_PROXY = "github.alecsio.mmceaddons.CommonProxy";

    // Registries handlers
    public static final RegistryItems REGISTRY_ITEMS = new RegistryItems();

    @Mod.Instance(MODID)
    public static ModularMachineryAddons instance;

    public static Logger logger;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("mmaddons"); // MODID could not be used here as the max length of the channel name must be < 20

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    public ModularMachineryAddons() {
        System.out.println("Initializing ModularMachineryAddons...");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = VERSION;
        logger = event.getModLog();

        proxy.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MachineAssemblyEventHandler());
        MinecraftForge.EVENT_BUS.register(new RightClickHandler());
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new MouseScrollHandler());
            MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
        }
        INSTANCE.registerMessage(MouseScrollMessageHandler.class, MouseScrollMessage.class, 0, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent serverStartEvent) {
        ModularMachineryAddons.logger.info("MMCEA: Server starting");
        serverStartEvent.registerServerCommand(new CommandGetCacheInfo());
    }
}
