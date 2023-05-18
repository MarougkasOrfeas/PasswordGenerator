package gr.marou.passwordGenerator.model;

/**
 * Enum representing different character sets used for password generation.
 */
public enum Digits {
    NUMBERS ("0123456789"),
    LOWERCASE_LETTERS ("abcdefghijklmnopqrstuvwxyz"),
    UPPERCASE_LETTERS ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    SPECIAL_CHARACTERS ("!@#$%^&*()_+?><:{}[]-_.,");

    private final String characters;
    Digits(String containing){

        this.characters = containing;
    }
    public String getCharacters() {
        return characters;
    }
}
