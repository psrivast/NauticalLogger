package main;

public class LogReader {

    LogsCache logsCache;

    public LogReader() {
        logsCache = LogsCache.getInstance();
    }

    public String get() {
        return logsCache.get();
    }
}
