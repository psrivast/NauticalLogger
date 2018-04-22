package main;

/**
 * Logger
 * Allows for logging of messages with prioirty and updates LogsCache
 */
public class Logger {

    private LogsCache logsCache;

    /**
     * Get copy of LogsCache singleton
     */
    public Logger() {
        logsCache = LogsCache.getInstance();
    }

    /**
     * Add log with given message and priority
     * @param priority
     * @param message
     */
    public void log(int priority, String message) {
        logsCache.put(priority, message);
    }

    /**
     * Add log with given message at HIGH priority
     * @param message
     */
    public void logHighPriority(String message) {
        log(3, message);
    }

    /**
     * Add log with given message at MED priority
     * @param message
     */
    public void logMedPriority(String message) {
        log(2, message);
    }

    /**
     * Add log with given message at LOW priority
     * @param message
     */
    public void logLowPriority(String message) {
        log(1, message);
    }
}
