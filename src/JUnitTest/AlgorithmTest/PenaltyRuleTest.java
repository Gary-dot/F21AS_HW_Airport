package JUnitTest.AlgorithmTest;

import model.Algorithm.PenaltyRule;
import model.DataStructure.Baggage;
import model.DataStructure.Flight;
import java.time.LocalTime;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PenaltyRuleTest {

    private Flight flight;

    @BeforeEach
    void setup(){
        Baggage baggage = new Baggage(24, 16, 10, 60);
        flight = new Flight("MC456", "C", "A", 100,
                baggage, 10000, 2000, LocalTime.of(10, 31));
    }

    /**
     * Test baggage 30x15x15, 50
     * Baggage limit 24x16x10, 50，
     * total penalty 110，weight:0，size:110
     */
    @Test
    public void penaltyRuleTest() {
        Baggage passengerBag = new Baggage(30, 15, 15, 50);
        int[] result = PenaltyRule.calculateExcessPenalty(flight, passengerBag);

        assertEquals(110, result[0], "Size excess penalty calculation is incorrect");
        assertEquals(0, result[1], "Weight excess penalty should be 0");
        assertEquals(110, result[2], "Total penalty calculation is incorrect");
    }

    /**
     * Test baggage 25x16x10, 50
     * Baggage limit 24x16x10, 60，
     * total penalty 50，weight:0，size:50
     */
    @Test
    public void testCalculateExcessPenalty_WeightExcess() {
        Baggage passengerBag = new Baggage(25, 20, 10, 50);
        int[] result = PenaltyRule.calculateExcessPenalty(flight, passengerBag);

        assertEquals(50, result[0], "Size excess penalty should be 0");
        assertEquals(0, result[1], "Weight excess penalty calculation is incorrect");
        assertEquals(50, result[2], "Total penalty calculation is incorrect");
    }

    /**
     * limit 24x16x10, 60
     * Test 20x10x10, 30
     * no penalty
     */
    @Test
    public void testCalculateExcessPenalty_NoExcess() {
        Baggage passengerBag = new Baggage(20, 10, 10, 30);
        int[] result = PenaltyRule.calculateExcessPenalty(flight, passengerBag);

        assertEquals(0, result[0], "Size excess penalty should be 0");
        assertEquals(0, result[1], "Weight excess penalty should be 0");
        assertEquals(0, result[2], "Total penalty should be 0");
    }
}
