package main;

public class Logger {

    private LogsCache logsCache;

    public Logger() {
        logsCache = LogsCache.getInstance();
    }

    public void log(int priority, String message) {
        logsCache.put(priority, message);
    }

    public void logHighPriority(String message) {
        log(3, message);
    }

    public void logMedPriority(String message) {
        log(2, message);
    }

    public void logLowPriority(String message) {
        log(1, message);
    }
}
