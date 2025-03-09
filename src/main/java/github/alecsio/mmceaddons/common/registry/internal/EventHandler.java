package github.alecsio.mmceaddons.common.registry.internal;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.crafting.component.ModularMachineryAddonComponents;
import github.alecsio.mmceaddons.common.crafting.requirement.types.AddonRequirements;
import github.alecsio.mmceaddons.common.registry.RegistryBlocks;
import github.alecsio.mmceaddons.common.registry.RegistryItems;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventHandler {
    public static class InternalRegistryPrimer {
        private final Map<Type, List<IForgeRegistryEntry<?>>> primed = new HashMap<>();

        public <V extends IForgeRegistryEntry<V>> V register(V entry) {
            Class<V> type = entry.getRegistryType();
            List<IForgeRegistryEntry<?>> entries = primed.computeIfAbsent(type, k -> new LinkedList<>());
            entries.add(entry);
            return entry;
        }

        <T extends IForgeRegistryEntry<T>> List<?> getEntries(Class<T> type) {
            return primed.get(type);
        }

        void wipe(Type type) {
            primed.remove(type);
        }
    }
    private static final InternalRegistryPrimer registry;

    static {
        registry = new InternalRegistryPrimer();
    }

    public EventHandler() {
        //ModularMachineryAddons.logger.info("📌 EventHandler class loaded!");

    }

    @SubscribeEvent
    public void onComponentTypeRegister(RegistryEvent.Register<ComponentType> event) {
        ModularMachineryAddonComponents.initComponents(event);
        for (ComponentType component : ModularMachineryAddonComponents.COMPONENTS) {
            event.getRegistry().register(component);
        }
    }



    @SubscribeEvent
    public void onRequirementTypeRegister(RegistryEvent.Register event) {
        if (event.getGenericType() != RequirementType.class) {
            return;
        }
        ModularMachineryAddons.logger.info("Registering custom requirements...");

        AddonRequirements.initRequirements();

        for (RequirementType<?, ?> requirement : AddonRequirements.REQUIREMENTS) {
            ResourceLocation name = requirement.getRegistryName();

            if (event.getRegistry().containsKey(name)) {
                ModularMachineryAddons.logger.error("⚠️ Duplicate registry entry: " + name);
                continue;
            }

            ModularMachineryAddons.logger.info("✅ Registering: " + name);
            event.getRegistry().register(requirement);
        }
    }

    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> event) {
        informAboutRegistrationFor(event);

        RegistryItems.initialise();

        event.getRegistry().registerAll(RegistryItems.ITEM_BLOCKS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> event) {
        informAboutRegistrationFor(event);

        RegistryBlocks.initialise();

        event.getRegistry().registerAll(RegistryBlocks.BLOCKS.toArray(new Block[0]));
    }

    private static void informAboutRegistrationFor(RegistryEvent.Register<?> event) {
        String registryName = event.getRegistry().getRegistrySuperType().getSimpleName();

        ModularMachineryAddons.logger.info("Registering " + registryName + "...");
    }
}
