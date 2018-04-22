package main;

/**
 * LogBuffer
 *
 * A thread-safe mutable sequence of character. Uses the StringBuffer
 */
public class LogBuffer {
    private StringBuffer sb;
    private LogsCache logsCache;
    private int priority;

    /**
     * Get instance of logsCache and sets priority
     *
     * @param priority
     */
    public LogBuffer(int priority) {
        this.sb = new StringBuffer();
        this.logsCache = LogsCache.getInstance();
        this.priority = priority;
    }

    /**
     * Appends the specified string
     *
     * @param str
     */
    public void append(String str) {
        sb.append(str);
    }

    /**
     * Appends the specified string and adds a new line
     *
     * @param str
     */
    public void appendNewLine(String str) {
        sb.append(str + "\n");
    }

    /**
     * Adds log message to cache and clears StringBuffer
     */
    public void done() {
        logsCache.put(priority, sb.toString());
        sb.delete(0, sb.length());
    }
}
