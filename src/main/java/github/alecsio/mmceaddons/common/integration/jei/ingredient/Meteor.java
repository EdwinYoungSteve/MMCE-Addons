package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import WayofTime.bloodmagic.meteor.MeteorComponent;
import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.stream.IntStream;

public class Meteor extends WayofTime.bloodmagic.meteor.Meteor implements IRequiresEquals<Meteor> {
    public Meteor(ItemStack catalystStack, List<MeteorComponent> components, float explosionStrength, int radius) {
        super(catalystStack, components, explosionStrength, radius);
    }

    @Override
    public boolean equalsTo(Meteor other) {
        return this.getComponents().equals(other.getComponents()) &&
                this.getRadius() == other.getRadius() &&
                this.getExplosionStrength() == other.getExplosionStrength() &&
                IntStream.range(0, this.getComponents().size()).allMatch(i -> {
                    var otherComponent = other.getComponents().get(i);
                    var thisComponent = this.getComponents().get(i);
                    return thisComponent.getWeight() == otherComponent.getWeight() && thisComponent.getOreName().equals(otherComponent.getOreName());
                });
    }
}
