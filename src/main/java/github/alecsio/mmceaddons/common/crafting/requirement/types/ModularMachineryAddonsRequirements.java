package github.alecsio.mmceaddons.common.crafting.requirement.types;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.base.Mods;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModularMachineryAddonsRequirements {
    public static final ResourceLocation KEY_REQUIREMENT_RADIATION = new ResourceLocation(ModularMachineryAddons.MODID, "radiation");
    public static final ResourceLocation KEY_REQUIREMENT_WILL_MULTI_CHUNK = new ResourceLocation(ModularMachineryAddons.MODID, "willMultiChunk");
    public static final ArrayList<RequirementType<?, ?>> REQUIREMENTS = new ArrayList<>();

    public static void initRequirements() {
        ModularMachineryAddons.logger.info("🔄 Initializing Addon Requirements...");
        REQUIREMENTS.clear(); // 🛑 Prevents duplicate registrations

        if (Mods.NUCLEARCRAFT.isPresent()) {
            registerRequirement(new RequirementTypeRadiation(), KEY_REQUIREMENT_RADIATION);
        }

        if (Mods.BLOODMAGIC.isPresent()) {
            registerRequirement(new RequirementTypeWillMultiChunk(), KEY_REQUIREMENT_WILL_MULTI_CHUNK);
        }
    }

    public static void registerRequirement(RequirementType<?, ?> requirement, ResourceLocation name) {
        requirement.setRegistryName(name);
        REQUIREMENTS.add(requirement);
    }
}
