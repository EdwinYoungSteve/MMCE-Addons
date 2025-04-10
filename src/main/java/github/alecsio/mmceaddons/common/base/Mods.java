package github.alecsio.mmceaddons.common.base;

import net.minecraftforge.fml.common.Loader;

public enum Mods {
    NUCLEARCRAFT("nuclearcraft"),
    BLOODMAGIC("bloodmagic"),
    THAUMICENERGISTICS("thaumicenergistics"),
    THAUMCRAFT("thaumcraft"),;

    // Used in annotations, which require compile-time constants. Yes, it's redundant. But it stil reduces boilerplate code
    public static final String NUCLEARCRAFT_ID = "nuclearcraft";
    public static final String BLOODMAGIC_ID = "bloodmagic";
    public static final String THAUMICENERGISTICS_ID = "thaumicenergistics";
    public static final String THAUMCRAFT_ID = "thaumcraft";

    public final String modid;
    private final boolean loaded;

    Mods(String modName) {
        this.modid = modName;
        this.loaded = Loader.isModLoaded(this.modid);
    }

    public boolean isPresent() {
        return loaded;
    }
}
