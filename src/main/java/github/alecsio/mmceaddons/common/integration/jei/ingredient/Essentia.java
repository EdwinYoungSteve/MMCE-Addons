package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.api.EssentiaStack;

public class Essentia extends EssentiaStack implements IRequiresEquals<Essentia> {
    public Essentia(Aspect aspect, int amount) {
        super(aspect, amount);
    }

    @Override
    public boolean equalsTo(Essentia other) {
        return getAmount() == other.getAmount() && getAspect().getTag().equals(other.getAspect().getTag());
    }
}
