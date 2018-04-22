package main;

/**
 * LogBuffer
 *
 * A thread-safe mutable sequence of character. Uses the StringBuffer
 */
public class LogBuffer {
       private StringBuffer sb;
    private Logger logger;
    private int priority;

    /**
     *
     * @param priority
     */
    public LogBuffer(int priority) {
        this.sb = new StringBuffer();
        this.logger = new Logger();
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
     *
     * @param str
     */
    public void appendNewLine(String str) {
        sb.append(str + "\n");
    }

    /**
     * Returns a string representing the data in this sequence.
     *
     * @return
     */
    public String toString() {
        return sb.toString();
    }

    /**
     * Clears string buffer
     */
    public void clear() {
        sb.delete(0, sb.length());
    }

    /**
     * Logs message and clears buffer. Priority is set to 0 to mark unitialized
     */
    public void done() {
        logger.log(priority, sb.toString());
        priority = 0;
        clear();
    }
}
