package github.alecsio.mmceaddons.common.integration.jei.component.base;

import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;

import java.util.List;

public abstract class JEIComponentBase<T> extends ComponentRequirement.JEIComponent<T>{
    private T requirement;
    private Class<T> requirementType;

    public JEIComponentBase(T requirement, Class<T> requirementType) {
        this.requirement = requirement;
        this.requirementType = requirementType;
    }

    @Override
    public Class<T> getJEIRequirementClass() {
        return requirementType;
    }

    @Override
    public List<T> getJEIIORequirements() {
        return List.of(requirement);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, T ingredient, List<String> tooltip) {}
}
