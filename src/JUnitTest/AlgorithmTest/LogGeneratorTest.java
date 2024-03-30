package JUnitTest.AlgorithmTest;

import model.Algorithm.LogGenerator;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogGeneratorTest {
    private LogGenerator logGenerator;
    @BeforeEach
    void setUp() {
        logGenerator = LogGenerator.getInstance();
    }

    /**
     * Test if the report is generated correctly.
     */
    @Test
    void generateLogFileTest() {
        logGenerator.addLog("This is a test log message.");

        // generate log file
        logGenerator.generateLogFile();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        String expectedFileName = now.format(formatter) + ".log";
        String LOGDICT = "./logs/";

        File logFile = new File(LOGDICT + expectedFileName);
        assertTrue(logFile.exists(), "Log file should have been created.");
    }
}
