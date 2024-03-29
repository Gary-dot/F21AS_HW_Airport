package JUnitTest.AlgorithmTest;

import java.nio.file.Path;
import java.nio.file.Paths;

import model.DataStructure.FlightList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListReaderTest {
    /**
     * test if list can be read correctly
     */
    @Test
    void testReadFlightListWithPath() {
        // with the new Constructor
        FlightList flightList = new FlightList("./input/test/TestFlightList.txt");
        System.out.println(flightList);
        // check if the list is read correctly
        assertEquals(2, flightList.size());
    }

}
