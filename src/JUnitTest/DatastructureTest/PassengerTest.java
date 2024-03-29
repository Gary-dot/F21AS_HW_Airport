package JUnitTest.DatastructureTest;

import model.DataStructure.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    public void setUp() {
        // new a passenger
        Baggage baggage = new Baggage(20, 15, 10, 10); // 假设的行李信息
        int[] penalty = {0, 0, 0};
        passenger = new Passenger("GB233", "Doe", "John", "FL123", true, baggage, penalty);
    }

    /**
     * make sure passenger info can be printed correctly
     */
    @Test
    public void printPassengerTest() {
        // System.out.println(passenger.printPassenger());
        String expectedOutput = "GB233      John      Doe        20x15x10;10\n";
        // 调用printPassenger方法并验证结果
        assertEquals(expectedOutput, passenger.printPassenger());
        System.out.println(passenger);
    }
}
