package github.alecsio.mmceaddons.common.crafting.requirement.Validator;

import github.alecsio.mmceaddons.common.exception.RequirementPrerequisiteFailedException;

public class RequirementValidator {

    private static RequirementValidator instance;

    public static RequirementValidator getInstance() {
        if (instance == null) {instance = new RequirementValidator();}
        return instance;
    }

    private RequirementValidator() {}

    public void validateNotNegative(double value, String errorMessage) {
        if (value < 0) {throw new RequirementPrerequisiteFailedException(errorMessage);}
    }

    public void validateNotNull(Object value, String errorMessage) {
        if (value == null) {throw new RequirementPrerequisiteFailedException(errorMessage);}
    }
}
