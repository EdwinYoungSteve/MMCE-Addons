package github.alecsio.mmceaddons.common.crafting.component;

import github.alecsio.mmceaddons.common.base.Mods;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;

import javax.annotation.Nullable;

public class ComponentVis extends ComponentType {
    @Nullable
    @Override
    public String requiresModid() {
        return Mods.THAUMCRAFT.modid;
    }
}
