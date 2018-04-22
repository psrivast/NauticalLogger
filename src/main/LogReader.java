package main;

import java.io.FileWriter;
import java.io.IOException;

public class LogReader {

    private LogsCache logsCache;
    private final static String OUTPUT_FILE = "logs.txt";

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
    public String dumpAllLogs() throws IOException {
        String logReport = logsCache.formattedDump();

        FileWriter fw = new FileWriter(OUTPUT_FILE);
        fw.write(logReport);
        fw.close();

        return logReport;
    }
}
