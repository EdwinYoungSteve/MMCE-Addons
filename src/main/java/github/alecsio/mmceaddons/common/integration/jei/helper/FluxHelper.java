package github.alecsio.mmceaddons.common.integration.jei.helper;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.integration.jei.helper.base.BaseIngredientHelper;
import github.alecsio.mmceaddons.common.integration.jei.ingredient.Flux;

import javax.annotation.Nullable;
import java.util.UUID;

public class FluxHelper extends BaseIngredientHelper<Flux> {

    @Override
    public String getDisplayName(Flux flux) {
        return "Flux";
    }

    @Override
    public String getUniqueId(Flux flux) {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getWildcardId(Flux flux) {
        return getUniqueId(flux);
    }

    @Override
    public String getModId(Flux flux) {
        return ModularMachineryAddons.MODID;
    }

    @Override
    public String getResourceId(Flux flux) {
        return null;
    }

    @Override
    public Flux copyIngredient(Flux flux) {
        return new Flux(flux.amount());
    }

    @Override
    public String getErrorInfo(@Nullable Flux flux) {
        return "";
    }
}
