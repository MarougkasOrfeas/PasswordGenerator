package gr.marou.password_generator.model.enums;

/**
 * ANSI escape codes for colored console output.
 */
public enum ASCIColors {
    /** Resets color to default. */
    RESET("\033[0m"),
    /** Black text color. */
    BLACK("\033[30m"),
    /** Red text color. */
    RED("\033[31m"),
    /** Green text color. */
    GREEN("\033[32m"),
    /** Yellow text color. */
    YELLOW("\033[33m"),
    /** Blue text color. */
    BLUE("\033[34m"),
    /** Purple text color. */
    PURPLE("\033[35m"),
    /** Cyan text color. */
    CYAN("\033[36m"),
    /** White text color. */
    WHITE("\033[37m");

    /** The ANSI escape code string. */
    private final String code;

    /**
     * Constructs an ASCIColors enum value.
     *
     * @param code ANSI escape code
     */
    ASCIColors(String code) {
        this.code = code;
    }

    /**
     * Returns the ANSI escape code.
     *
     * @return the escape code string
     */
    public String getCode() {
        return code;
    }

    /**
     *  To String method.
     * @return the code value String.
     */
    @Override
    public String toString() {
        return code;
    }

}
