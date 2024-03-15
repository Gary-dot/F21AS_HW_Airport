package JUnitTest.DatastructureTest;

import model.DataStructure.Flight;
import model.DataStructure.FlightList;
import model.DataStructure.Baggage;

import java.time.LocalTime;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightListTest {
    private FlightList flightList;
    @BeforeEach
    void setUp() {
        flightList = FlightList.getInstance();
        flightList.getArrayList().clear(); // Clean up the singleton instance so that each test starts with a clean state
    }

    /**
     * test if details can be added correctly
     */
    @Test
    void addDetailsTest(){
        Flight f = new Flight("CA888", "C", "A", 100,
                new Baggage(50, 30, 20, 15),
                10000, 2000, LocalTime.of(10, 31));
        flightList.addDetails(f);
        assertEquals(1, flightList.size());
    }

    /**
     * test if flightlist is listed by departure time
     */
    @Test
    void listByDepartureTimeTest(){
        Flight f1 = new Flight("MC456", "C", "A", 100,
                new Baggage(50, 30, 20, 15),
                10000, 2000, LocalTime.of(10, 31));
        Flight f2 = new Flight("MH655", "B", "B", 180,
                new Baggage(60, 40, 30, 20),
                10000, 2000, LocalTime.of(9, 28));
        Flight f3 = new Flight("CH321", "A", "C", 120,
                new Baggage(70, 45, 35, 25),
                10000, 2000, LocalTime.of(11, 6));

        flightList.addDetails(f3);
        flightList.addDetails(f2);
        flightList.addDetails(f1);

        flightList.listByDepartureTime();

        assertEquals(flightList.getArrayList().get(0).getDepartureTime(), LocalTime.of(9, 28));
        assertEquals(flightList.getArrayList().get(1).getDepartureTime(), LocalTime.of(10, 31));
        assertEquals(flightList.getArrayList().get(2).getDepartureTime(), LocalTime.of(11, 6));
    }
}
