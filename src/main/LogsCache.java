package main;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LogsCache
 * Singleton class that contains a cache of all logs
 * and provides interface for retrieval
 */

public class LogsCache {

    private Map<Integer, Stack<Log>> logsCache;

    private static LogsCache ourInstance = new LogsCache();

    public static LogsCache getInstance() {
        return ourInstance;
    }

    /**
     * Initialize logs cache as ConcurrentHashMap to guarantee thread safe access
     * Three entries are added (ascending priority):
     * - Low Priority (1)
     * - Med Priority (2)
     * - High Priority (3)
     */
    private LogsCache() {
        this.logsCache = new ConcurrentHashMap<Integer, Stack<Log>>();
        Stack<Log> highPriorityLogs = new Stack<Log>();
        Stack<Log> medPriorityLogs = new Stack<Log>();
        Stack<Log> lowPriorityLogs = new Stack<Log>();
        this.logsCache.put(3, highPriorityLogs);
        this.logsCache.put(2, medPriorityLogs);
        this.logsCache.put(1, lowPriorityLogs);
    }

    /**
     * Retrieve message that is:
     * 1) Highest Priority
     * 2) Most recent
     * Remove message from stack
     * When messages are empty, return error message
     *
     * @return
     */
    public String get(boolean formatted) {
        for(int i = 3; i > 0; i--) {
            if (!logsCache.get(i).isEmpty()) {
                return  formatted ?
                        logsCache.get(i).pop().getFormattedMessage():
                        logsCache.get(i).pop().getMessage();
            }
        }
        return "Arg! There are no more logs...";
    }

    /**
     * Add message for given priority
     *
     * @param priority
     * @param message
     */
    public void put(int priority, String message) {
        Log log = new Log(priority, message);
        logsCache.get(priority).push(log);
    }

    /**
     * Clear all entries
     */
    public void clearLogs() {
        for(Stack<Log> logs : logsCache.values()) {
            logs.clear();
        }
    }


}
