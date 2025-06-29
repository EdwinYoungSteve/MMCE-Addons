package github.alecsio.mmceaddons.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtil {

    public static int getOrDefault(ItemStack stack, String key, int defaultValue) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            return defaultValue;
        }
        return tag.getInteger(key);
    }

}
