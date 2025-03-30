package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Essentia;

import javax.annotation.Nonnull;
import java.util.UUID;

public class EssentiaHelper extends BaseIngredientHelper<Essentia> {

    @Override
    public String getResourceId(@Nonnull Essentia t) {
        return t.getAspect().getImage().getPath();
    }

    @Override
    @Nonnull
    public String getDisplayName(@Nonnull Essentia essentiaStack) {
        return essentiaStack.getAspect().getName();
    }

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull Essentia essentiaStack) {
        return essentiaStack.getAspect().getTag() + UUID.randomUUID();
    }

    @Override
    @Nonnull
    public Essentia copyIngredient(@Nonnull Essentia essentiaStack) {
        return new Essentia(essentiaStack.getAspect(), essentiaStack.getAmount());
    }
}
