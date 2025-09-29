package util;

import java.io.IOException;
import java.util.logging.*;

/**
 * Configure logging for the application (console + file).
 */
public final class LoggerConfig {
    private static final Logger root = Logger.getLogger("");

    static {
        setup();
    }

    private LoggerConfig() {}

    private static void setup() {
        // Remove default handlers
        Handler[] handlers = root.getHandlers();
        for (Handler h : handlers) {
            root.removeHandler(h);
        }

        // Console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());
        root.addHandler(consoleHandler);

        // File handler (app.log)
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            root.addHandler(fileHandler);
        } catch (IOException e) {
            root.log(Level.WARNING, "Failed to create log file handler", e);
        }

        root.setLevel(Level.ALL);
    }
}
