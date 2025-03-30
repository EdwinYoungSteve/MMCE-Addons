package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import WayofTime.bloodmagic.meteor.MeteorComponent;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Meteor extends WayofTime.bloodmagic.meteor.Meteor {
    public Meteor(ItemStack catalystStack, List<MeteorComponent> components, float explosionStrength, int radius) {
        super(catalystStack, components, explosionStrength, radius);
    }
}
