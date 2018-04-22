package main;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LogsCache
 * Singleton class that contains a cache of all logs
 * and provides interface for retrieval
 */

public class LogsCache {

    private Map<Integer, Stack<String>> logsCache;

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
        this.logsCache = new ConcurrentHashMap<Integer, Stack<String>>();
        Stack<String> highPriorityMessages = new Stack<String>();
        Stack<String> medPriorityMessages = new Stack<String>();
        Stack<String> lowPriorityMessages = new Stack<String>();
        this.logsCache.put(3, highPriorityMessages);
        this.logsCache.put(2, medPriorityMessages);
        this.logsCache.put(1, lowPriorityMessages);
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
    public String get() {
        for(int i = 3; i > 0; i--) {
            if (!logsCache.get(i).isEmpty()) {
                return logsCache.get(i).pop();
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
        logsCache.get(priority).push(message);
    }

    /**
     * Clear all entries
     */
    public void clearLogs() {
        for(Stack<String> messages : logsCache.values()) {
            messages.clear();
        }
    }


}
