import gr.marou.passwordGenerator.model.PasswordGenerator;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that generates and analyzes passwords based on user input.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.setLevel(Level.INFO);
        logger.info("Enter the length of the password you want me to generate: ");
        int digits = readPasswordLength();

        String password = new PasswordGenerator().generatePassword(digits);
        logger.info(password);

        showPasswordStrength(digits);
    }

    /**
     * Determines and displays the strength of the generated password based on its length and character types.
     * @param digits the length of the password
     */
    private static void showPasswordStrength(int digits) {
        int strengthScore = passwordGenerator.calculatePasswordStrength(digits);
        if (digits >= 10 && strengthScore >= 3) {
            logger.info("Strong password");
        } else if (digits >= 6 && strengthScore >= 2) {
            logger.info("Medium password");
        } else {
            logger.info("Weak password");
        }
    }

    /**
     * Reads and validates the password length input from the user.
     * @return the valid password length entered by the user
     */
    private static int readPasswordLength() {
        while (true) {
            int digits = scanner.nextInt();
            if (digits > 0) {
                return digits;
            }
            logger.info("Invalid input. Please enter a positive number: ");
        }
    }
}