package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.wrapper.AspectListWrapper;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.plugins.jei.JEIInternalPlugin;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutEssentia extends RecipeLayoutPart<AspectListWrapper> {
    private final AspectListWrapper wrapper;
    public LayoutEssentia(Point offset, AspectListWrapper wrapper) {
        super(offset);
        this.wrapper = wrapper;
    }

    @Override
    public int getComponentWidth() {
        int count = wrapper != null ? wrapper.getAspects().length : 1;
        int itemsPerRow = getMaxHorizontalCount();
        int rows = (int) Math.ceil((double) count / itemsPerRow);  // Calculate rows needed
        return rows > 1 ? (16 * itemsPerRow) + (getComponentHorizontalGap() * (itemsPerRow - 1)) : 16 * count;
    }

    @Override
    public int getComponentHeight() {
        int count = wrapper != null ? wrapper.getAspects().length : 1;
        int itemsPerRow = getMaxHorizontalCount();
        int rows = (int) Math.ceil((double) count / itemsPerRow);  // Calculate rows
        return rows * (16 + getComponentVerticalGap());
    }

    @Override
    public Class<AspectListWrapper> getLayoutTypeClass() {
        return AspectListWrapper.class;
    }

    @Override
    public IIngredientRenderer<AspectListWrapper> provideIngredientRenderer() {
        return JEIInternalPlugin.ingredientRegistry.getIngredientRenderer(new AspectListWrapper());
    }

    @Override
    public int getRendererPaddingX() {
        return 0;
    }

    @Override
    public int getRendererPaddingY() {
        return 0;
    }

    @Override
    public int getMaxHorizontalCount() {
        return 8;
    }

    @Override
    public int getComponentHorizontalGap() {
        return 1;
    }

    @Override
    public int getComponentVerticalGap() {
        return 1;
    }

    @Override
    public int getComponentHorizontalSortingOrder() {
        return 0;
    }

    @Override
    public boolean canBeScaled() {
        return false;
    }

    @Override
    public void drawBackground(Minecraft mc) {

    }
}
