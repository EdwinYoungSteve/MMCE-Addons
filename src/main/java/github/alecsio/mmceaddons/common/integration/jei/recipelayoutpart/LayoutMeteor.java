package github.alecsio.mmceaddons.common.integration.jei.recipelayoutpart;

import WayofTime.bloodmagic.meteor.Meteor;
import github.alecsio.mmceaddons.common.integration.jei.render.MeteorRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutMeteor extends RecipeLayoutPart<Meteor> {


    public LayoutMeteor(Point offset) {
        super(offset);
    }

    @Override
    public int getComponentWidth() {
        return 18;
    }

    @Override
    public int getComponentHeight() {
        return 18;
    }

    @Override
    public Class<Meteor> getLayoutTypeClass() {
        return Meteor.class;
    }

    @Override
    public IIngredientRenderer<Meteor> provideIngredientRenderer() {
        return new MeteorRenderer();
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
        return 2;
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
        return 10;
    }

    @Override
    public boolean canBeScaled() {
        return false;
    }

    @Override
    public void drawBackground(Minecraft minecraft) {

    }
}
