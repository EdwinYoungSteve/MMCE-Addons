package github.alecsio.mmceaddons.common.integration.jei.helper;

import com.google.common.collect.Iterables;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import mezz.jei.api.ingredients.IIngredientHelper;
import thaumicenergistics.api.EssentiaStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EssentiaHelper implements IIngredientHelper<EssentiaStack> {
    @Nullable
    @Override
    public EssentiaStack getMatch(Iterable<EssentiaStack> ingredients, EssentiaStack toMatch) {
        if (Iterables.isEmpty(ingredients)) {return null;}

        for (EssentiaStack ingredient : ingredients) {
            if (ingredient.getAmount() == toMatch.getAmount() && ingredient.getAspect().getTag().equals(toMatch.getAspect().getTag())) {
                return ingredient;
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public String getWildcardId(@Nonnull EssentiaStack t) {
        return getUniqueId(t);
    }

    @Override
    public String getResourceId(@Nonnull EssentiaStack t) {
        return null;
    }

    @Override
    @Nonnull
    public String getModId(@Nonnull EssentiaStack t) {
        return ModularMachineryAddons.MODID;
    }

    @Override
    @Nonnull
    public String getErrorInfo(@Nullable EssentiaStack t) {
        return "error"; // todo: fix
    }

    @Override
    @Nonnull
    public String getDisplayName(@Nonnull EssentiaStack essentiaStack) {
        return essentiaStack.getAspect().getName();
    }

    @Override
    @Nonnull
    public String getUniqueId(@Nonnull EssentiaStack essentiaStack) {
        return essentiaStack.getAspect().getTag();
    }

    @Override
    @Nonnull
    public EssentiaStack copyIngredient(@Nonnull EssentiaStack essentiaStack) {
        return new EssentiaStack(essentiaStack.getAspect().getTag(), essentiaStack.getAmount());
    }
}
