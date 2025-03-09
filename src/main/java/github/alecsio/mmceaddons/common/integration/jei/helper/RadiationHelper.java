package github.alecsio.mmceaddons.common.integration.jei.helper;

import com.google.common.collect.Iterables;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Radiation;
import mezz.jei.api.ingredients.IIngredientHelper;

import javax.annotation.Nullable;

public class RadiationHelper<T extends Radiation> implements IIngredientHelper<T> {
    @Nullable
    @Override
    public T getMatch(Iterable<T> ingredients, T ingredientToMatch) {
        if (Iterables.isEmpty(ingredients)) return null;

        for (T ingredient : ingredients) {
            if (ingredient.getAmount() == ingredientToMatch.getAmount()) return ingredient;
        }
        return null;
    }

    @Override
    public String getDisplayName(T t) {
        return "Radiation";
    }

    @Override
    public String getUniqueId(T t) {
        return "Radiation";
    }

    @Override
    public String getWildcardId(T t) {
        return "Radiation";
    }

    @Override
    public String getModId(T t) {
        return ModularMachineryAddons.MODID;
    }

    @Override
    public String getResourceId(T t) {
        return null;
    }

    @Override
    public T copyIngredient(T t) {
        return t;
    }

    @Override
    public String getErrorInfo(@Nullable T t) {
        return "error";
    }
}
