package gr.marou.password_generator.cli;

import gr.marou.password_generator.exception.PasswordGenerationException;
import gr.marou.password_generator.model.enums.ASCIColors;
import gr.marou.password_generator.model.enums.PasswordSizeNumbers;
import gr.marou.password_generator.model.enums.StrengthLevel;
import gr.marou.password_generator.service.PasswordService;
import java.util.Objects;
import java.util.logging.Logger;
import static gr.marou.password_generator.util.LogUtils.*;

/**
 * Runs the command-line interface for the password generator.
 */
public class CLIRunner {

    /** Logger for displaying output messages. */
    private final Logger log;
    /** Service for password generation and evaluation. */
    private final PasswordService passwordService;

    /**
     * Creates a new CLIRunner with logger and password service.
     *
     * @param log Logger to use
     * @param passwordService Service to generate and evaluate passwords
     */
    public CLIRunner(Logger log, PasswordService passwordService) {
        this.log = log;
        this.passwordService = passwordService;
    }

    /**
     * Executes the CLI with provided arguments.
     *
     * @param args Command-line arguments
     */
    public void run(String[] args) {
        printHeader();

        Integer length = checkPasswordLength(args);
        if (Objects.isNull(length)) return;

        try {
            String password = passwordService.generatePassword(length);
            StrengthLevel strength = passwordService.evaluateStrength(password);
            info(log, "Generated Password: " + password, ASCIColors.GREEN);
            info(log, "Password Strength: " + strength.getValue(), StrengthLevel.getStrengthColor(strength));
        } catch (PasswordGenerationException e) {
            error(log, e.getMessage(), ASCIColors.RED);
        } catch (Exception e) {
            error(log, "Internal Server Error: " + e.getMessage(), ASCIColors.RED);
        }
    }

    /**
     * Validates and parses the password length from arguments.
     *
     * @param args CLI arguments
     * @return Password length or null if invalid
     */
    private Integer checkPasswordLength(String[] args) {
        if (args.length == 0) {
            int defaultLength = passwordService.getDefaultLength();
            warning(log, "Using default length: " + defaultLength, ASCIColors.YELLOW);
            return defaultLength;
        }

        if (args.length == 1 && isNumeric(args[0])) {
            int length = Integer.parseInt(args[0]);
            info(log, "Using argument length: " + length, ASCIColors.YELLOW);
            if (length < PasswordSizeNumbers.MIN_LENGTH.getValue() || length > PasswordSizeNumbers.MAX_LENGTH.getValue()) {
                error(log, String.format(
                        "Invalid length: %d. Allowed range is [%d-%d].",
                        length,
                        PasswordSizeNumbers.MIN_LENGTH.getValue(),
                        PasswordSizeNumbers.MAX_LENGTH.getValue()
                ), ASCIColors.RED);
                printUsage();
                return null;
            }
            return length;
        }

        error(log, "Invalid argument. Length must be a numeric value.", ASCIColors.RED);
        printUsage();
        return null;
    }

    /**
     * Checks if the input string is numeric.
     *
     * @param input Input string
     * @return True if numeric, otherwise false
     */
    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Prints the CLI header.
     */
    private void printHeader() {
        info(log, """
                =========================================
                ||      Strong Password Generator      ||
                =========================================
                """, ASCIColors.PURPLE);
    }

    /**
     * Prints usage instructions.
     */
    private void printUsage() {
        warning(log, String.format("""
                        Usage: java Main [length]
                         - [length] must be a number between %d and %d.
                         - If no argument is provided, default length of %d is used.
                        """,
                PasswordSizeNumbers.MIN_LENGTH.getValue(),
                PasswordSizeNumbers.MAX_LENGTH.getValue(),
                PasswordSizeNumbers.DEFAULT_LENGTH.getValue()
        ));
    }
}

