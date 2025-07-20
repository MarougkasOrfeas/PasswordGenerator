package gr.marou.password_generator.util;

import gr.marou.password_generator.exception.PasswordGenerationException;
import gr.marou.password_generator.model.enums.ASCIColors;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for simplified logging with optional ANSI colors.
 */
public class LogUtils {

    /** Private constructor to prevent instantiation. */
    private LogUtils() {}

    /**
     * Configures the root logger to use a simple format and no default handlers.
     */
    public static void configureRootLogger() {
        Logger rootLogger = Logger.getLogger("");
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        var consoleHandler = new java.util.logging.ConsoleHandler();
        consoleHandler.setFormatter(new java.util.logging.SimpleFormatter() {
            @Override
            public synchronized String format(java.util.logging.LogRecord record) {
                return record.getMessage() + System.lineSeparator();
            }
        });

        rootLogger.addHandler(consoleHandler);
        rootLogger.setLevel(Level.ALL);
    }

    /**
     * Logs an info-level message.
     */
    public static void info(Logger logger, String message) {
        if (message != null && !message.isBlank()){
            log(logger, Level.INFO, message);
        }
    }

    /**
     * Logs a warning-level message.
     */
    public static void warning(Logger logger, String message) {
        if (message != null && !message.isBlank()) {
            log(logger, Level.WARNING, message);
        }
    }

    /**
     * Logs an error-level message.
     */
    public static void error(Logger logger, String message) {
        if (message != null && !message.isBlank()) {
            log(logger, Level.SEVERE, message);
        }
    }

    /**
     * Logs a colored info-level message.
     */
    public static void info(Logger logger, String message, ASCIColors color) {
        if (message != null && !message.isBlank()) {
            log(logger, Level.INFO, wrapColor(message, color));
        }
    }

    /**
     * Logs a colored warning-level message.
     */
    public static void warning(Logger logger, String message, ASCIColors color) {
        if (message != null && !message.isBlank()) {
            log(logger, Level.WARNING, wrapColor(message, color));
        }
    }

    /**
     * Logs a colored error-level message.
     */
    public static void error(Logger logger, String message, ASCIColors color) {
        if (message != null && !message.isBlank()) {
            log(logger, Level.SEVERE, wrapColor(message, color));
        }
    }

    /**
     * Logs a message at the specified level.
     */
    private static void log(Logger logger, Level level, String message) {
        if (logger == null || level == null) {
            throw new PasswordGenerationException("Logger Error, level, and message must not be null.");
        }
        if (logger.isLoggable(level)) {
            logger.log(level, message);
        }
    }

    /**
     * Wraps a message in ANSI color codes.
     */
    private static String wrapColor(String message, ASCIColors color) {
        return (color != null ? color.toString() : "") + message + ASCIColors.RESET;
    }
}
