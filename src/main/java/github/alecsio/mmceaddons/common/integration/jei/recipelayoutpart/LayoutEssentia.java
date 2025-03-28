package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import github.alecsio.mmceaddons.common.integration.jei.render.EssentiaRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import thaumicenergistics.api.EssentiaStack;

import java.awt.*;

public class LayoutEssentia extends RecipeLayoutPart<EssentiaStack> {
    public LayoutEssentia(Class<EssentiaStack> clazz, Point offset) {
        super(offset);
    }

    @Override
    public IIngredientRenderer<EssentiaStack> provideIngredientRenderer() {
        return new EssentiaRenderer();
    }

    @Override
    public int getComponentWidth() {
        return 16;
    }

    @Override
    public int getComponentHeight() {
        return 16;
    }

    @Override
    public Class<EssentiaStack> getLayoutTypeClass() {
        return EssentiaStack.class;
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
        return 4;
    }

    @Override
    public int getComponentHorizontalGap() {
        return 0;
    }

    @Override
    public int getComponentVerticalGap() {
        return 0;
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
    public void drawBackground(Minecraft mc) {}
}
