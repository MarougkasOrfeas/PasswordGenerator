package gr.marou.password_generator.model.enums;

/**
 * Enum representing different character sets used for password generation.
 */
public enum Digits {
    /** Numeric characters (0–9). */
    NUMBERS ("0123456789"),
    /** Lowercase alphabet (a–z). */
    LOWERCASE_LETTERS ("abcdefghijklmnopqrstuvwxyz"),
    /** Uppercase alphabet (A–Z). */
    UPPERCASE_LETTERS ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    /** Common special characters. */
    SPECIAL_CHARACTERS ("!@#$%^&*()_+?><:{}[]-_.,");

    /** Characters in this group. */
    private final String characters;

    /**
     * Creates a character group.
     *
     * @param containing Characters to include
     */
    Digits(String containing){
        this.characters = containing;
    }

    /**
     * Gets the characters for this group.
     *
     * @return Character string
     */
    public String getCharacters() {
        return characters;
    }
}
