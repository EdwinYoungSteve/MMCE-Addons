package github.alecsio.mmceaddons.common.integration.jei.helper;

import com.google.common.collect.Iterables;
import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.wrapper.AspectListWrapper;
import mezz.jei.api.ingredients.IIngredientHelper;

import javax.annotation.Nullable;

public class EssentiaHelper<T extends AspectListWrapper> implements IIngredientHelper<T> {
    @Nullable
    @Override
    public T getMatch(Iterable<T> ingredients, T toMatch) {
        if (Iterables.isEmpty(ingredients)) return null;

        for (T ingredient : ingredients) {
            if (ingredient.getAspects()[0].getTag().equalsIgnoreCase(toMatch.getAspects()[0].getTag()))
                return ingredient;
        }
        return null;
    }



    @Override
    public String getDisplayName(T t) {
        return t.getAspects()[0].getName();
    }

    @Override
    public String getUniqueId(T t) {
        return t.getAspects()[0].getTag();
    }

    @Override
    public String getWildcardId(T t) {
        return t.getAspects()[0].getTag();
    }

    @Override
    public String getModId(T t) {
        return ModularMachineryAddons.MODID;
    }

    @Override
    public String getResourceId(T t) {
        return "";
    }

    @Override
    public T copyIngredient(T t) {
        return t;
    }

    @Override
    public String getErrorInfo(@Nullable T t) {
        return null;
    }
}
