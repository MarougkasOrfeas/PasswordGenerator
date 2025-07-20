package gr.marou.password_generator;

import gr.marou.password_generator.cli.CLIRunner;
import gr.marou.password_generator.service.PasswordService;
import gr.marou.password_generator.service.PasswordServiceImpl;

import static gr.marou.password_generator.util.LogUtils.*;
import java.util.logging.*;

/**
 * Entry point of the password generator CLI application.
 * <p>
 * It initializes logging, sets up the password service, and runs the CLI logic.
 */
public class Main {

    /** Application logger for CLI output. */
    private static final Logger log = Logger.getLogger(Main.class.getName());

    // Configure logging on class load to exclude time and level outputs.
    static {
        configureRootLogger();
    }

    /**
     * Main method that launches the password generator.
     *
     * @param args Command-line arguments (optional password length)
     */
    public static void main(String[] args) {
        PasswordService passwordService = new PasswordServiceImpl();
        new CLIRunner(log, passwordService).run(args);
    }

}