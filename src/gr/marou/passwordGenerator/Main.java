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
        logger.info("""
                Use a minimum password length of 8 or more characters.
                Include lowercase and uppercase alphabetic characters, numbers and symbols.
                Avoid using the same password twice (e.g., across multiple user accounts and/or software systems).
                Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences,
                usernames, relative or pet names, romantic links (current or past) and biographical information (e.g., ID numbers, ancestors' names or dates).
                Avoid using information that the user's colleagues and/or acquaintances might know to be associated with the user.
                Do not use passwords which consist wholly of any simple combination of the aforementioned weak components.
                """);
        System.out.println("Enter the length of the password you want me to generate: ");
        int digits = readPasswordLength();

        String password = new PasswordGenerator().generatePassword(digits);
        System.out.println("[PASSWORD]: "+"\033[33m" + password + "\033[0m");

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
            logger.warning("Medium password");
        } else {
            logger.warning("Weak password");
        }
    }

    /**
     * Reads and validates the password length input from the user.
     * @return the valid password length entered by the user
     */
    private static int readPasswordLength() {
        while (true) {
            String input = scanner.nextLine();
            try{
                int digits = Integer.parseInt(input);
                if (digits > 0) {
                    return digits;
                }
            }catch (NumberFormatException e){
                logger.log(Level.SEVERE, e.getMessage() );
            }
            logger.warning("Invalid input. Please enter a positive number: ");
        }
    }
}