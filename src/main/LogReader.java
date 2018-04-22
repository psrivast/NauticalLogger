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
        return logsCache.get(false);
    }

    /**
     * Retrieve highest priority, most recent message
     * with additional information such as priority, timestamp, etc.
     * @return
     */
    public String getFormatted() {
        return logsCache.get(true);
    }

    /**
     * Dumps logs into a text file in current directory and
     * returns string representation
     * @return
     */
    public String seeAllLogs() {
        String logReport = logsCache.formattedDump();
        System.out.println(logReport);
        return logReport;
    }
}
