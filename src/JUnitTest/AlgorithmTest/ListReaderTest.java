package JUnitTest.AlgorithmTest;

import java.nio.file.Path;
import java.nio.file.Paths;

import model.DataStructure.FlightList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListReaderTest {
    private final Path testFilePath = Paths.get("./input/test/TestFlightList.txt");

    /**
     * test if list can be read correctly
     */
    @Test
    void testReadFlightListWithPath() {
        // with the new Constructor
        FlightList flightList = new FlightList(testFilePath.toString());
        // 验证是否正确读取了航班信息
        assertEquals(2, flightList.size());
    }

}
