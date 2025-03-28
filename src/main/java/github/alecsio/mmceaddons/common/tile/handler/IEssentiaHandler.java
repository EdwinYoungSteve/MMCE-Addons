package github.alecsio.mmceaddons.common.tile.handler;

import github.alecsio.mmceaddons.common.crafting.requirement.thaumicenergistics.RequirementEssentia;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;

public interface IEssentiaHandler {
    CraftCheck canHandle(RequirementEssentia essentia);
    boolean handle(RequirementEssentia essentia);
}
