package github.alecsio.mmceaddons.common.exception;

// Indicates that a prerequisite was not met while building a requirement
public class RequirementPrerequisiteFailedException extends RuntimeException {
    public RequirementPrerequisiteFailedException(String message) {
        super(message);
    }
}
