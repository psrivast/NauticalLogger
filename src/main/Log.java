package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.IllegalArgumentException;

/**
 * Log
 * Object representation of Log with priority and timestamp
 * The purpose of this class is to decorate the Log with additional fields
 */
public class Log {
    private String timestamp;
    private int priority;
    private String message;

    /**
     * Instantiate priority and message from inputs
     * Set timestamp to string representation of new Date object
     *
     * @param priority
     * @param message
     */
    public Log(int priority, String message){
        this.priority = priority;
        this.message = message;
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    /**
     * Return just the message part of the log
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Return a formatted message with timestamp and priority
     * 
     * @return
     */
    public String getFormattedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(timestamp);
        sb.append("]: ");
        sb.append("(");
        sb.append(getPriorityString(priority));
        sb.append(") ");
        sb.append(message);
        return sb.toString();
    }

    /**
     * Returns string representation of priority
     * Throws exception if !(1<=priority<=3)
     *
     * @param priority
     * @return
     * @throws IllegalArgumentException
     */
    private String getPriorityString(int priority) throws IllegalArgumentException {
        switch (priority) {
            case 1:
                return "LOW";
            case 2:
                return "MED";
            case 3:
                return "HIGH";
        }
        throw new IllegalArgumentException();
    }
}
