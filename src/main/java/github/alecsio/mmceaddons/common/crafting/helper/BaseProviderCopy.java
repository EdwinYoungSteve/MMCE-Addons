package github.alecsio.mmceaddons.common.crafting.helper;

import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;

public abstract class BaseProviderCopy<T> {

    protected final IRequirementHandler<T> original;

    public BaseProviderCopy(IRequirementHandler<T> original) {
        this.original = original;
    }

    public IRequirementHandler<T> getOriginal() {
        return original;
    }
}
