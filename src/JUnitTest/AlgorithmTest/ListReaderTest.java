package JUnitTest.AlgorithmTest;

import java.nio.file.Path;
import java.nio.file.Paths;

import model.DataStructure.FlightList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListReaderTest {
    private final Path testFilePath = Paths.get("./input/test/TestFlightList.txt");

    @Test
    void testReadFlightListWithPath() {
        // 通过测试文件路径创建FlightList实例
        FlightList flightList = new FlightList(testFilePath.toString());
        // 验证是否正确读取了航班信息
        assertEquals(2, flightList.size());
    }

}
