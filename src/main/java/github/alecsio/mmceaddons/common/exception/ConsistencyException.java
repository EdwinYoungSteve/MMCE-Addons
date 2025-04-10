package github.alecsio.mmceaddons.common.exception;

// Indicates an unexpected invariant violation
public class ConsistencyException extends RuntimeException {
    public ConsistencyException(String message) {
        super(message);
    }
}
