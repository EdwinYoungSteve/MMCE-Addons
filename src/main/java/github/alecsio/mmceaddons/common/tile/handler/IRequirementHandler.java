package github.alecsio.mmceaddons.common.tile.handler;

import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;

public interface IRequirementHandler<T> {
    CraftCheck canHandle(T requirement);
    void handle(T requirement);
}
