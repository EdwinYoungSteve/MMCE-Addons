package github.alecsio.mmceaddons.common.crafting.helper;

import github.alecsio.mmceaddons.common.crafting.requirement.bloodmagic.RequirementMeteor;
import github.alecsio.mmceaddons.common.tile.bloodmagic.TileMeteorProvider;
import github.alecsio.mmceaddons.common.tile.handler.IRequirementHandler;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;

public class MeteorProviderCopy extends BaseProviderCopy<RequirementMeteor> implements IRequirementHandler<RequirementMeteor> {

    private boolean reserved;

    public MeteorProviderCopy(IRequirementHandler<RequirementMeteor> original) {
        super(original);
        this.reserved = ((TileMeteorProvider.Output) original).reserved.get();
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        reserved = true;
    }

    @Override
    public CraftCheck canHandle(RequirementMeteor requirement) {
        CraftCheck check = isReserved() ? CraftCheck.failure(":(") : CraftCheck.success();

        if (check.isSuccess()) {
            reserve();
        }
        return check;
    }

    @Override
    public void handle(RequirementMeteor requirement) {
        original.handle(requirement);
    }
}
