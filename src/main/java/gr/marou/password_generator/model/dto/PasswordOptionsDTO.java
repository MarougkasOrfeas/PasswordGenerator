package gr.marou.password_generator.model.dto;

import java.util.List;

/**
 * Holds character groups used for password generation.
 *
 * @param numbers           List of digit characters
 * @param lowercaseLetters  List of lowercase characters
 * @param uppercaseLetters  List of uppercase characters
 * @param specialCharacters List of special characters
 */
public record PasswordOptionsDTO(List<Character> numbers, List<Character> lowercaseLetters,
                                 List<Character> uppercaseLetters, List<Character> specialCharacters) {}
