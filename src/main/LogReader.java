package main;

public class LogReader {

    LogsCache logsCache;

    /**
     * Get copy of LogsCache singleton
     */
    public LogReader() {
        logsCache = LogsCache.getInstance();
    }

    /**
     * Retrieve highest priority, most recent message
     * @return
     */
    public String get() {
        return logsCache.get();
    }
}
