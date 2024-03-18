package JUnitTest.DatastructureTest;

import model.DataStructure.*;
import java.time.LocalTime;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class PassengerListTest {

    private PassengerList passengerList;
    private FlightDetailsList flightDetailsList;
    @BeforeEach
    public void setUp(){
        flightDetailsList = FlightDetailsList.getInstance();
        passengerList = new PassengerList();

        Baggage baggageLimit = new Baggage(20, 15, 12, 50);
        Flight flight = new Flight("GB233", "A", "China Eastern", 200, baggageLimit, 20000, 300000, LocalTime.of(10, 30));
        FlightDetails flightDetails = new FlightDetails(flight);
        flightDetailsList.addDetails(flightDetails);
    }

    @Test
    void addARandomPassengerIncreasesListSizeTest() {
        int initialSize = passengerList.size();
        passengerList.addARandomPassenger();
        int newSize = passengerList.size();
        assertEquals(initialSize + 1, newSize);

        for(int i = 0; i <= 3; i++){
            passengerList.addARandomPassenger();
        }
        int newSize2 = passengerList.size();
        assertEquals(initialSize + 5, newSize2);
    }
}
