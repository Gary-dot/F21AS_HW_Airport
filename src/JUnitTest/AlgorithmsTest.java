package JUnitTest;

import Algorithm.PenaltyRule;
import Algorithm.ReadLists;
import DataStructure.*;

//JUnit 4
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;

//JUnit 5, including
//Test: This annotation denotes that a method is a test method.
//@BeforeEach: This annotation denotes that the annotated method will be executed before each test method.
//@AfterEach: This annotation denotes that the annotated method will be executed after each test method.
//@BeforeAll: This annotation denotes that the annotated method will be executed before all test methods in the current class.
//@AfterAll: This annotation denotes that the annotated method will be executed after all test methods in the current class.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
// This is main.

public class AlgorithmsTest {
    /**
     * Test the calculateExcessPenalty method in PenaltyRule class.
     */
    @Test
    public void testCalculateExcessPenalty() {
        Baggage limit = new Baggage(22, 14, 9, 50);
        Flight f1 = new Flight("CX0001", "Hong Kon", "A Airline", 100, limit, 100, 1000);
        Passenger p1 = new Passenger("2313", "A", "B", "CX0001", true);
        p1.setBaggage(new Baggage(27, 14, 10, 60));
        int[] penalty = PenaltyRule.calculateExcessPenalty(f1, p1.getBaggage());
        assertEquals(60, penalty[0]);
        assertEquals(250, penalty[1]);
        assertEquals(310, penalty[2]);
    }

    /**
     * Test if both FlightList and PassengerList are read correctly.
     */
    @Test
    public void testReadLists() {
        PassengerList passengerList = new PassengerList();
        FlightList flightList = new FlightList();
        FlightDetailsList flightDetailsList = ReadLists.readLists(passengerList, flightList);
        System.out.println(flightList);
        System.out.println(passengerList);
        System.out.println(flightDetailsList);
        assertEquals(10, flightList.size());
        assertEquals(20, passengerList.size());
        assertEquals(10, flightDetailsList.size());
    }
}
