package JUnitTest.AlgorithmTest;

import model.Algorithm.LogGenerator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogGeneratorTest {
    @Test
    public void testLogGenerator() {
        LogGenerator.getInstance().generateLogFile();
        assertEquals(1,1);
    }
}
