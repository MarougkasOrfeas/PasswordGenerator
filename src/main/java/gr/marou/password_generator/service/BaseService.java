package gr.marou.password_generator.service;

import gr.marou.password_generator.exception.PasswordGenerationException;
import gr.marou.password_generator.model.enums.PasswordSizeNumbers;
import gr.marou.password_generator.model.enums.StrengthLevel;
import gr.marou.password_generator.util.PasswordStrengthUtils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class providing common password utilities.
 */
public abstract class BaseService {

    /** Secure random generator for password operations. */
    protected final SecureRandom random = new SecureRandom();

    /**
     * Shuffles a list of characters using a secure random source.
     *
     * @param characters the list to shuffle
     */
    protected void shuffle(List<Character> characters) {
        if (characters == null || characters.isEmpty()) {
            throw new PasswordGenerationException("Cannot shuffle a null or empty character list.");
        }
        Collections.shuffle(characters, random);
    }

    /**
     * Returns the default password length.
     *
     * @return the default length as defined in PasswordSizeNumbers enum
     */
    protected int getDefaultLength() {
        return PasswordSizeNumbers.DEFAULT_LENGTH.getValue();
    }

    /**
     * Evaluates the strength of a given password based on length, character variety, and uniqueness.
     *
     * @param password the password string to evaluate
     * @return StrengthLevel representing the strength of the password
     */
    protected StrengthLevel evaluateStrength(String password) {
        return PasswordStrengthUtils.evaluate(password);
    }
}
