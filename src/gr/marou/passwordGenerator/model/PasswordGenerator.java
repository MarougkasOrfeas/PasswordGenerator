package gr.marou.passwordGenerator.model;

import gr.marou.passwordGenerator.model.Digits;

import java.security.SecureRandom;

/**
 * Utility class for generating passwords and calculating password strength.
 */
public class PasswordGenerator {

    private final SecureRandom secureRandom ;

    public PasswordGenerator(){
        this.secureRandom = new SecureRandom();
    }

    /**
     * Generates a password with the specified number of digits.
     * @param digits the number of digits in the generated password
     * @return the generated password
     */
    public String generatePassword(int digits) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < digits; i++) {
            Digits digit = getRandomDigit();
            String characters = digit.getCharacters();
            int charIndex = secureRandom.nextInt(characters.length());
            password.append(characters.charAt(charIndex));
        }

        return password.toString();
    }

    /**
     * Calculates the password strength based on the number of digits and the presence of different character types.
     * @param digits the number of digits in the password
     * @return the password strength score
     */
    public int calculatePasswordStrength(int digits){
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < digits; i++) {
            Digits digit = getRandomDigit();
            String characters = digit.getCharacters();
            int charIndex = secureRandom.nextInt(characters.length());
            char randomChar = characters.charAt(charIndex);

            if (Character.isLowerCase(randomChar)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(randomChar)) {
                hasUpperCase = true;
            } else if (Character.isDigit(randomChar)) {
                hasNumber = true;
            } else {
                hasSpecialCharacter = true;
            }
        }
        return calculateStrengthScore(hasLowerCase,hasUpperCase,hasNumber,hasSpecialCharacter);
    }

    /**
     * Calculates the strength score based on the presence of different character types.
     * @param hasLowerCase        indicates if the password has at least one lowercase letter
     * @param hasUpperCase        indicates if the password has at least one uppercase letter
     * @param hasNumber           indicates if the password has at least one digit
     * @param hasSpecialCharacter indicates if the password has at least one special character
     * @return the strength score based on the presence of different character types
     */
    private int calculateStrengthScore( boolean hasLowerCase, boolean hasUpperCase, boolean hasNumber, boolean hasSpecialCharacter){
        int strengthScore = 0;
        if (hasLowerCase) {
            strengthScore++;
        }
        if (hasUpperCase) {
            strengthScore++;
        }
        if (hasNumber) {
            strengthScore++;
        }
        if (hasSpecialCharacter) {
            strengthScore++;
        }
        return strengthScore;
    }

    /**
     * Retrieves a random digit from the available digit types.
     * @return a random digit from the available digit types
     */
    private Digits getRandomDigit() {
        Digits[] values = Digits.values();
        int randIndex = secureRandom.nextInt(values.length);
        return values[randIndex];
    }
}
