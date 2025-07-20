package gr.marou.password_generator.service;

import gr.marou.password_generator.exception.PasswordGenerationException;
import gr.marou.password_generator.model.dto.PasswordOptionsDTO;
import gr.marou.password_generator.model.enums.StrengthLevel;
import gr.marou.password_generator.util.PasswordOptionsUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service implementation for generating and evaluating passwords.
 */
public class PasswordServiceImpl extends BaseService implements PasswordService{

    /** Holds the available character groups. */
    private final PasswordOptionsDTO characterOptions;

    /**
     * Constructs the password service with predefined character sets.
     */
    public PasswordServiceImpl() {
        this.characterOptions = PasswordOptionsUtils.createDTOFromEnum();
        validateCharacterOptions(characterOptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefaultLength() {
        return super.getDefaultLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StrengthLevel evaluateStrength(String password) {
        return super.evaluateStrength(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePassword(Integer length) {
        if (length == null || length < 4) {
            throw new PasswordGenerationException("Password length must be at least 4 to include all required character types.");
        }
        checkGlobalCharacterLimit(length);
        // Keep track of used digits
        Set<Character> usedChars = new HashSet<>();
        //Add one character from each group as a starting point
        List<Character> passwordChars = initializePassword(usedChars);
        // Get all character groups as final list to rotate
        List<List<Character>> groups = getCharacterGroups();
        // Proceed to construct the final password, cycle threw the groups and pick a random char from each.
        fillRemainingCharacters(passwordChars, usedChars, groups, length);
        // Shuffle password characters
        shuffle(passwordChars);
        // Return final password string
        return buildPasswordString(passwordChars);
    }

    /**
     * Validates character groups for non-emptiness.
     *
     * @param dto character options DTO
     */
    private void validateCharacterOptions(PasswordOptionsDTO dto) {
        if (dto.numbers().isEmpty() || dto.lowercaseLetters().isEmpty() ||
                dto.uppercaseLetters().isEmpty() || dto.specialCharacters().isEmpty()) {
            throw new PasswordGenerationException("Character groups must not be empty.");
        }
    }

    /**
     * Adds one unique character from each group to start the password.
     *
     * @param usedChars the set of characters already used
     * @return the initial list of characters
     */
    private List<Character> initializePassword(Set<Character> usedChars) {
        if (usedChars == null) {
            throw new PasswordGenerationException("Used character set cannot be null.");
        }
        List<Character> result = new ArrayList<>();
        result.add(getUniqueCharFromGroup(characterOptions.numbers(), usedChars));
        result.add(getUniqueCharFromGroup(characterOptions.lowercaseLetters(), usedChars));
        result.add(getUniqueCharFromGroup(characterOptions.uppercaseLetters(), usedChars));
        result.add(getUniqueCharFromGroup(characterOptions.specialCharacters(), usedChars));
        return result;
    }

    /**
     * Gets all character groups (number, lower, upper, special) as a list.
     *
     * @return list of character groups
     */
    private List<List<Character>> getCharacterGroups() {
        return List.of(
                characterOptions.numbers(),
                characterOptions.lowercaseLetters(),
                characterOptions.uppercaseLetters(),
                characterOptions.specialCharacters()
        );
    }

    /**
     * Fills the password to the desired length by rotating through character groups.
     *
     * @param passwordChars current password characters
     * @param usedChars     set of characters already used
     * @param groups        character groups to use
     * @param desiredLength the final desired length of the password
     */
    private void fillRemainingCharacters(List<Character> passwordChars,
                                         Set<Character> usedChars,
                                         List<List<Character>> groups,
                                         int desiredLength) {
        int groupIndex = 0;
        while (passwordChars.size() < desiredLength) {
            List<Character> currentGroup = groups.get(groupIndex % groups.size());
            char nextChar = getUniqueCharFromGroup(currentGroup, usedChars);
            passwordChars.add(nextChar);
            usedChars.add(nextChar);
            groupIndex++;
        }
    }

    /**
     * Builds the final password string from the character list.
     *
     * @param characters the list of characters
     * @return the final password string
     */
    private String buildPasswordString(List<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            throw new PasswordGenerationException("No characters to build password.");
        }
        StringBuilder password = new StringBuilder();
        characters.forEach(password::append);
        return password.toString();
    }

    /**
     * Returns a unique character from a group not in the used set.
     *
     * @param group character group
     * @param used  already used characters
     * @return unique character
     */
    private char getUniqueCharFromGroup(List<Character> group, Set<Character> used) {
        List<Character> available = group.stream()
                .filter(c -> !used.contains(c))
                .toList();

        if (available.isEmpty()) {
            throw new PasswordGenerationException("No unique characters left in group.");
        }

        return available.get(random.nextInt(available.size()));
    }

    /**
     * Checks if the requested length exceeds the total available characters.
     *
     * @param desiredLength password length
     */
    private void checkGlobalCharacterLimit(int desiredLength) {
        Set<Character> totalUnique = new HashSet<>();
        totalUnique.addAll(characterOptions.numbers());
        totalUnique.addAll(characterOptions.lowercaseLetters());
        totalUnique.addAll(characterOptions.uppercaseLetters());
        totalUnique.addAll(characterOptions.specialCharacters());

        if (desiredLength > totalUnique.size()) {
            throw new PasswordGenerationException(
                    String.format("Requested password length (%d) exceeds the number of unique characters available (%d).",
                            desiredLength, totalUnique.size()));
        }
    }
}
