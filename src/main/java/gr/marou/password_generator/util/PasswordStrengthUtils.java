package gr.marou.password_generator.util;

import gr.marou.password_generator.model.enums.StrengthLevel;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for evaluating password strength.
 */
public class PasswordStrengthUtils {

    /** Private constructor to prevent instantiation. */
    private PasswordStrengthUtils(){}

    /**
     * Evaluates the strength of a password using variety, length, and uniqueness.
     *
     * @param password the password to evaluate
     * @return the strength level
     */
    public static StrengthLevel evaluate(String password) {
        if (password == null || password.isBlank()) return StrengthLevel.WEAK;

        int length = password.length();
        int variety = countVariety(password);
        double uniquenessRatio = calculateUniquenessRatio(password);

        return determineStrength(length, variety, uniquenessRatio);
    }

    /**
     * Counts the number of different character types in the password.
     */
    private static int countVariety(String password) {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;
        }

        int variety = 0;
        if (hasLower) variety++;
        if (hasUpper) variety++;
        if (hasDigit) variety++;
        if (hasSpecial) variety++;

        return variety;
    }

    /**
     * Calculates the uniqueness ratio of the characters in the password.
     */
    private static double calculateUniquenessRatio(String password) {
        Set<Character> unique = new HashSet<>();
        for (char ch : password.toCharArray()) {
            unique.add(ch);
        }
        return (double) unique.size() / password.length();
    }

    /**
     * Determines the strength level from metrics.
     */
    private static StrengthLevel determineStrength(int length, int variety, double uniquenessRatio) {
        if (length < 8 || variety < 2 || uniquenessRatio < 0.5) {
            return StrengthLevel.WEAK;
        } else if (length <= 10 && variety >= 2 && uniquenessRatio >= 0.5) {
            return StrengthLevel.FAIR;
        } else if (length <= 14 && variety == 4 && uniquenessRatio >= 0.7) {
            return StrengthLevel.STRONG;
        } else if (length > 14 && variety == 4 && uniquenessRatio >= 0.8) {
            return StrengthLevel.VERY_STRONG;
        }
        return StrengthLevel.FAIR;
    }

}
