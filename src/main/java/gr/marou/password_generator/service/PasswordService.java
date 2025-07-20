package gr.marou.password_generator.service;

import gr.marou.password_generator.model.enums.StrengthLevel;

/**
 * Interface defining password generation and evaluation operations.
 */
public interface PasswordService {

    /**
     * Generates a password of the given length.
     *
     * @param length desired password length
     * @return generated password
     */
    String generatePassword(Integer length);

    /**
     * Gets the default password length.
     *
     * @return default length
     */
    int getDefaultLength();

    /**
     * Evaluates the strength of the given password.
     *
     * @param password password string
     * @return strength level
     */
    StrengthLevel evaluateStrength(String password);
}
