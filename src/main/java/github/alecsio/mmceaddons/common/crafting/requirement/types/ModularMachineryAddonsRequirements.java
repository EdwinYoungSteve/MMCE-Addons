package github.alecsio.mmceaddons.common.crafting.requirement.types;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.base.Mods;
import github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic.RequirementTypeMeteor;
import github.alecsio.mmceaddons.common.crafting.requirement.types.bloodmagic.RequirementTypeWillMultiChunk;
import github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft.RequirementTypeRadiation;
import github.alecsio.mmceaddons.common.crafting.requirement.types.nuclearcraft.RequirementTypeRadiationPerTick;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModularMachineryAddonsRequirements {
    public static final ResourceLocation KEY_REQUIREMENT_RADIATION = new ResourceLocation(ModularMachineryAddons.MODID, "radiation");
    public static final ResourceLocation KEY_REQUIREMENT_RADIATION_PER_TICK = new ResourceLocation(ModularMachineryAddons.MODID, "scrubber");
    public static final ResourceLocation KEY_REQUIREMENT_METEOR = new ResourceLocation(ModularMachineryAddons.MODID, "meteor");
    public static final ResourceLocation KEY_REQUIREMENT_WILL_MULTI_CHUNK = new ResourceLocation(ModularMachineryAddons.MODID, "willMultiChunk");
    public static final ArrayList<RequirementType<?, ?>> REQUIREMENTS = new ArrayList<>();

    public static void initRequirements() {
        ModularMachineryAddons.logger.info("🔄 Initializing Addon Requirements...");
        REQUIREMENTS.clear(); // 🛑 Prevents duplicate registrations

        if (Mods.NUCLEARCRAFT.isPresent()) {
            registerRequirement(new RequirementTypeRadiation(), KEY_REQUIREMENT_RADIATION);
            registerRequirement(new RequirementTypeRadiationPerTick(), KEY_REQUIREMENT_RADIATION_PER_TICK);
        }

        if (Mods.BLOODMAGIC.isPresent()) {
            registerRequirement(new RequirementTypeWillMultiChunk(), KEY_REQUIREMENT_WILL_MULTI_CHUNK);
            registerRequirement(new RequirementTypeMeteor(), KEY_REQUIREMENT_METEOR);
        }
    }

    public static void registerRequirement(RequirementType<?, ?> requirement, ResourceLocation name) {
        requirement.setRegistryName(name);
        REQUIREMENTS.add(requirement);
    }
}
