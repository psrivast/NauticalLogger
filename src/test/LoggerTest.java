package test;

import main.LogReader;
import main.Logger;
import main.LogsCache;
import org.junit.Test;
import org.junit.Assert;

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

    @Test
    public void BasicTests() {
        Logger logger = new Logger();
        logger.log(1, sailorMsg1);
        logger.log(2, sailorMsg2);
        logger.log(1, sailorMsg3);

        LogReader logReader = new LogReader();
        Assert.assertEquals(logReader.get(), sailorMsg2);
        Assert.assertEquals(logReader.get(), sailorMsg3);
        Assert.assertEquals(logReader.get(), sailorMsg1);
        Assert.assertEquals(logReader.get(), emptyMsg);

        clearLogs();
    }

    @Test
    public void MultipleLoggersTest() {
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

        clearLogs();
    }

    @Test
    public void PriorityLoggingTest() {
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

        clearLogs();
    }

}
