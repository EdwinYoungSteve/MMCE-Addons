package github.alecsio.mmceaddons.mixin;

import hellfirepvp.modularmachinery.ModularMachinery;
import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class MMCEALateMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> configs = new ArrayList<>();
        configs.add("mixins.mmcea_minecraft.json");
        return configs;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        if (mixinConfig.equals("mmcea_minecraft.json")) {
            return Loader.isModLoaded(ModularMachinery.MODID);
        } else {
            return true;
        }
    }
}
