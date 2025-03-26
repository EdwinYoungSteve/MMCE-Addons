package github.alecsio.mmceaddons.common.crafting.component;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.base.Mods;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class ModularMachineryAddonsComponents {

    public static IForgeRegistry<ComponentType> COMPONENT_TYPE_REGISTRY;
    public static final ResourceLocation KEY_COMPONENT_RADIATION = new ResourceLocation(ModularMachineryAddons.MODID, "radiation");
    public static final ResourceLocation KEY_COMPONENT_METEOR = new ResourceLocation(ModularMachineryAddons.MODID, "meteor");
    public static final ResourceLocation KEY_COMPONENT_WILL = new ResourceLocation(ModularMachineryAddons.MODID, "willMultiChunk");

    public static final ArrayList<ComponentType> COMPONENTS = new ArrayList<>();

    public static void initComponents(RegistryEvent.Register<ComponentType> event) {
        ModularMachineryAddons.logger.info("Registering component: " + KEY_COMPONENT_RADIATION);
        COMPONENT_TYPE_REGISTRY = event.getRegistry();
        if (Mods.NUCLEARCRAFT.isPresent()) {
            registerComponent(new ComponentRadiation(), KEY_COMPONENT_RADIATION);
        }

        if (Mods.BLOODMAGIC.isPresent()) {
            registerComponent(new ComponentWillMultiChunk(), KEY_COMPONENT_WILL);
            registerComponent(new ComponentMeteor(), KEY_COMPONENT_METEOR);
        }
    }

    public static void registerComponent(ComponentType component, ResourceLocation name) {
        component.setRegistryName(name);
        if (COMPONENTS.contains(component)) {
            ModularMachineryAddons.logger.error("Component " + name + " already registered! This is probably a skill issue.");
            return;
        }
        COMPONENTS.add(component);
    }
}
