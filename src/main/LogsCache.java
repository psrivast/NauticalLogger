package main;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LogsCache {

    private Map<Integer, Stack<String>> logsCache;

    private static LogsCache ourInstance = new LogsCache();

    public static LogsCache getInstance() {
        return ourInstance;
    }

    private LogsCache() {
        this.logsCache = new ConcurrentHashMap<Integer, Stack<String>>();
        Stack<String> highPriorityMessages = new Stack<String>();
        Stack<String> medPriorityMessages = new Stack<String>();
        Stack<String> lowPriorityMessages = new Stack<String>();
        this.logsCache.put(3, highPriorityMessages);
        this.logsCache.put(2, medPriorityMessages);
        this.logsCache.put(1, lowPriorityMessages);
    }

    public String get() {
        for(int i = 3; i > 0; i--) {
            if (!logsCache.get(i).isEmpty()) {
                return logsCache.get(i).pop();
            }
        }

        return "Arg! There are no more logs...";
    }

    public void put(int priority, String message) {
        logsCache.get(priority).push(message);
    }

    public void clearLogs() {
        for(Stack<String> messages : logsCache.values()) {
            messages.clear();
        }
    }


}
