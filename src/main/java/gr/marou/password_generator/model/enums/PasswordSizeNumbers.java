package gr.marou.password_generator.model.enums;

/**
 * Defines allowed password length settings.
 */
public enum PasswordSizeNumbers {
    /** Default password length. */
    DEFAULT_LENGTH(10),
    /** Minimum allowed length. */
    MIN_LENGTH(8),
    /** Maximum allowed length. */
    MAX_LENGTH(40);

    /** Numeric value for the setting. */
    private final int value;

    /**
     * Sets the value.
     *
     * @param value Length value
     */
    PasswordSizeNumbers(int value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return Length as int
     */
    public int getValue() {
        return value;
    }

}
