package github.alecsio.mmceaddons.common.integration.jei.helper.base;

import com.google.common.collect.Iterables;
import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;
import mezz.jei.api.ingredients.IIngredientHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BaseIngredientHelper<T extends IRequiresEquals<T>> implements IIngredientHelper<T> {
    @Nullable
    @Override
    public T getMatch(@Nonnull Iterable<T> ingredients, @Nonnull T toMatch) {
        if(Iterables.isEmpty(ingredients)) {return null;}

        for (T ingredient : ingredients) {
            if (ingredient.equalsTo(toMatch)) {return ingredient;};
        }
        return null;
    }
}
