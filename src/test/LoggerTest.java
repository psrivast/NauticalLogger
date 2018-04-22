package test;

import main.LogBuffer;
import main.LogReader;
import main.Logger;
import main.LogsCache;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

public class LoggerTest {

    private String sailorMsg1 = "Avast, mateys! Let’s weigh anchor here.";
    private String sailorMsg2 = "Raise the Jolly Roger!";
    private String sailorMsg3 = "That scallywag traded me empty coconuts for safe passage to shore.";
    private String sailorMsg4 = "Well, shiver me timbers, it’s the Captain’s ghost after all!";
    private String sailorMsg5 = "Aye, aye captain!";
    private String emptyMsg = "Arg! There are no more logs...";

    private void clearLogs() {
        LogsCache.getInstance().clearLogs();
    }

    /**
     * This is the included test for basic functionality with Loggers
     */
    @Test
    public void BasicTests() {
        clearLogs();

        Logger logger = new Logger();
        logger.log(1, sailorMsg1);
        logger.log(2, sailorMsg2);
        logger.log(1, sailorMsg3);

        LogReader logReader = new LogReader();
        Assert.assertEquals(logReader.get(), sailorMsg2);
        Assert.assertEquals(logReader.get(), sailorMsg3);
        Assert.assertEquals(logReader.get(), sailorMsg1);
        Assert.assertEquals(logReader.get(), emptyMsg);
    }

    /**
     * This tests the behavior if we instantiate multiple loggers
     */
    @Test
    public void MultipleLoggersTest() {
        clearLogs();

        Logger logger1 = new Logger();
        Logger logger2 = new Logger();
        Logger logger3 = new Logger();
        logger1.log(1, sailorMsg1);
        logger2.log(2, sailorMsg2);
        logger3.log(1, sailorMsg3);

        LogReader logReader = new LogReader();
        Assert.assertEquals(logReader.get(), sailorMsg2);
        Assert.assertEquals(logReader.get(), sailorMsg3);
        Assert.assertEquals(logReader.get(), sailorMsg1);
        Assert.assertEquals(logReader.get(), emptyMsg);
    }

    /**
     * This tests the priority logging functions:
     * - logHighPriority
     * - logMedPriority
     * - logLowPriority
     */
    @Test
    public void PriorityLoggingTest() {
        clearLogs();

        Logger logger = new Logger();
        logger.logHighPriority(sailorMsg4);
        logger.logMedPriority(sailorMsg1);
        logger.logMedPriority(sailorMsg3);
        logger.logLowPriority(sailorMsg5);

        LogReader logReader = new LogReader();
        Assert.assertEquals(logReader.get(), sailorMsg4);
        Assert.assertEquals(logReader.get(), sailorMsg3);
        Assert.assertEquals(logReader.get(), sailorMsg1);
        Assert.assertEquals(logReader.get(), sailorMsg5);
        logReader.get();
    }

    /**
     * This tests the log buffer functionality
     */
    @Test
    public void LogBufferTest() {
        clearLogs();

        LogBuffer lb1 = new LogBuffer(1);
        LogBuffer lb2 = new LogBuffer(2);
        LogBuffer lb3 = new LogBuffer(3);
        lb1.appendNewLine(sailorMsg1);
        lb2.append(sailorMsg2);
        lb1.append(sailorMsg3);
        lb3.append("This is ");
        lb3.append("a test :)");
        lb3.done();
        lb2.done();
        lb1.done();

        LogReader logReader = new LogReader();
        Assert.assertEquals(logReader.get(), "This is a test :)");
        Assert.assertEquals(logReader.get(), sailorMsg2);
        Assert.assertEquals(logReader.get(), sailorMsg1 + "\n" + sailorMsg3);
        Assert.assertEquals(logReader.get(), emptyMsg);
    }

    /**
     * This test allows us to see the Formatting of logs.
     * (Cannot assert here due to unpredictable timestamp)
     */
    //@Test
    public void SeeFormattedLogs(){
        clearLogs();

        Logger logger = new Logger();
        logger.log(1, sailorMsg1);
        logger.log(2, sailorMsg2);
        logger.log(1, sailorMsg3);

        LogReader logReader = new LogReader();
        System.out.println(logReader.getFormatted());
        System.out.println(logReader.getFormatted());
        System.out.println(logReader.getFormatted());
        System.out.println(logReader.getFormatted());
    }

    /**
     * Tests working with a LogBuffer and a Logger in the same workflow
     */
    @Test
    public void LogBufferAndLoggerTest() {
        clearLogs();

        Logger logger = new Logger();
        LogBuffer lb = new LogBuffer(2);
        logger.log(1, sailorMsg1);
        lb.append("This is ");
        logger.log(3, sailorMsg4);
        lb.append("a test :)");
        lb.done();

        LogReader logReader = new LogReader();
        Assert.assertEquals(logReader.get(), sailorMsg4);
        Assert.assertEquals(logReader.get(), "This is a test :)");
        Assert.assertEquals(logReader.get(), sailorMsg1);
        Assert.assertEquals(logReader.get(), emptyMsg);
    }

    /**
     * Prints all logs to standard out
     */
    @Test
    public void SeeAllLogs() throws IOException {
        clearLogs();

        Logger logger = new Logger();
        logger.logHighPriority(sailorMsg4);
        logger.logMedPriority(sailorMsg3);
        logger.logMedPriority(sailorMsg2);
        logger.logLowPriority(sailorMsg1);
        logger.logLowPriority(sailorMsg5);

        LogReader logReader = new LogReader();
        System.out.println(logReader.dumpAllLogs());
    }
}
