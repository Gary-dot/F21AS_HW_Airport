package JUnitTest.AlgorithmTest;

import model.Algorithm.PassengerGenerator;
import model.DataStructure.*;
import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerGeneratorTest {
    private FlightDetailsList flightDetailsList;

    @BeforeEach
    void setUp() {
        flightDetailsList = FlightDetailsList.getInstance();

        Baggage baggageLimit = new Baggage(20, 15, 12, 50);
        Flight flight = new Flight("GB233", "A", "China Eastern", 200, baggageLimit, 20000, 300000, LocalTime.of(10, 30));
        FlightDetails flightDetails = new FlightDetails(flight);
        flightDetailsList.addDetails(flightDetails);
    }

    /**
     * make sure passenger is generated correctly
     */
    @Test
    void testGenerateARandomPassenger() {
        Passenger passenger = PassengerGenerator.generateARandomPassenger(flightDetailsList);
        assertNotNull(passenger);
        assertEquals("GB233", passenger.getFlightCode());
    }
}
