package gr.marou.password_generator.exception;

/**
 * Thrown when password generation fails due to invalid input or constraints.
 */
public class PasswordGenerationException extends RuntimeException{

    /**
     * Creates a new exception with a message.
     *
     * @param message Error message
     */
    public PasswordGenerationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with a message and cause.
     *
     * @param message Error message
     * @param cause   Underlying cause
     */
    public PasswordGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
