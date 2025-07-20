package gr.marou.password_generator.model.enums;

/**
 * Represents the strength level of a password.
 */
public enum StrengthLevel {

    /** Weak password. */
    WEAK("Weak"),
    /** Fair password. */
    FAIR("Fair"),
    /** Strong password. */
    STRONG("Strong"),
    /** Very strong password. */
    VERY_STRONG("Very Strong");

    /** Label for the strength level. */
    private final String value;

    /**
     * Sets the label.
     *
     * @param value Display text
     */
    StrengthLevel(String value){
        this.value = value;
    }

    /**
     * Gets the label.
     *
     * @return Text value
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets the color associated with the strength level.
     *
     * @param level Strength level
     * @return Corresponding ASCI color
     */
    public static ASCIColors getStrengthColor(StrengthLevel level) {
        return switch (level) {
            case WEAK -> ASCIColors.RED;
            case FAIR -> ASCIColors.YELLOW;
            case STRONG -> ASCIColors.BLUE;
            case VERY_STRONG -> ASCIColors.GREEN;
        };
    }
}
