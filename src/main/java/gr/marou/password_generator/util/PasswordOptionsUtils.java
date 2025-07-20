package gr.marou.password_generator.util;

import gr.marou.password_generator.exception.PasswordGenerationException;
import gr.marou.password_generator.model.enums.Digits;
import gr.marou.password_generator.model.dto.PasswordOptionsDTO;
import java.util.List;

/**
 * Utility class for creating character options from enum values.
 */
public class PasswordOptionsUtils {

    /** Private constructor to prevent instantiation. */
    private PasswordOptionsUtils(){}

    /**
     * Creates a DTO containing all character types for password generation.
     *
     * @return DTO with character lists
     */
    public static PasswordOptionsDTO createDTOFromEnum() {
        return new PasswordOptionsDTO(
                toCharList(Digits.NUMBERS),
                toCharList(Digits.LOWERCASE_LETTERS),
                toCharList(Digits.UPPERCASE_LETTERS),
                toCharList(Digits.SPECIAL_CHARACTERS)
        );
    }

    /**
     * Converts a digit enum to a list of characters.
     *
     * @param digits the enum value
     * @return list of characters
     */
    private static List<Character> toCharList(Digits digits) {
        if (digits == null || digits.getCharacters() == null || digits.getCharacters().isBlank()) {
            throw new PasswordGenerationException("Invalid Digits enum or empty character set.");
        }
        return digits.getCharacters()
                .chars()
                .mapToObj(c -> (char) c)
                .toList();
    }
}
