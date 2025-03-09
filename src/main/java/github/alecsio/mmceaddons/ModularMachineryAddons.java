package github.alecsio.mmceaddons;

import github.alecsio.mmceaddons.common.registry.RegistryBlocks;
import github.alecsio.mmceaddons.common.registry.RegistryItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;
//import org.spongepowered.asm.mixin.Mixins;

@Mod(
        modid = ModularMachineryAddons.MODID,
        name = ModularMachineryAddons.NAME,
        version = ModularMachineryAddons.VERSION,
        dependencies = "required-after:forge@[14.21.0.2371,);"
                + "required-after:modularmachinery@[1.11.1,);"
                + "after:jei@[4.13.1.222,);"
        ,
        acceptedMinecraftVersions = "[1.12]",
        acceptableRemoteVersions = "[2.1.0, 2.2.0)"
)
public class ModularMachineryAddons {

    public static final String MODID = "modularmachineryaddons";
    public static final String NAME = "Modular Machinery: Community Edition Addons";
    public static final String VERSION = "2.1.0"; // 🔹 FIXED: Avoid using Tags.VERSION
    public static final String CLIENT_PROXY = "github.alecsio.mmceaddons.client.ClientProxy";
    public static final String COMMON_PROXY = "github.alecsio.mmceaddons.common.registry.CommonProxy";

    // Registries handlers
    public static final RegistryItems REGISTRY_ITEMS = new RegistryItems();
    public static final RegistryBlocks REGISTRY_BLOCKS = new RegistryBlocks();

    @Mod.Instance(MODID)
    public static ModularMachineryAddons instance;
    public static Logger logger;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    public static final SimpleNetworkWrapper NET_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    public ModularMachineryAddons() {
        System.out.println("Initializing ModularMachineryAddons...");

        //Mixins.addConfiguration("mixins.mmcea_minecraft.json");

        // 🚀 FIXED: Move this to preInit()
        // proxy.preInit();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = VERSION;
        logger = event.getModLog();
        proxy.preInit(event); // 🚀 FIXED: Now runs at the correct time
        MinecraftForge.EVENT_BUS.register(this); // 🚀 FIXED: Now instance-based
    }
}
