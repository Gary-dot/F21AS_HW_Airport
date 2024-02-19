package JUnitTest;

import DataStructure.Baggage;
import DataStructure.Exceptions.WrongBaggageSizeFormatException;
//JUnit 4
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;

//JUnit 5, including
//@Test: This annotation denotes that a method is a test method.
//@BeforeEach: This annotation denotes that the annotated method will be executed before each test method.
//@AfterEach: This annotation denotes that the annotated method will be executed after each test method.
//@BeforeAll: This annotation denotes that the annotated method will be executed before all test methods in the current class.
//@AfterAll: This annotation denotes that the annotated method will be executed after all test methods in the current class.
import DataStructure.Baggage;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
// This is main.

public class DataStructureTest {
    /**
     * Test the Baggage class and WrongBaggageSizeFormatException class.
     */
    @Test
    public void testBaggage() {
        Baggage baggage = new Baggage(22, 14, 9, 50);
        assertEquals(22, baggage.getLength());
        assertEquals(14, baggage.getWidth());
        assertEquals(9, baggage.getHeight());
        assertEquals(50, baggage.getWeight());
        assertEquals(2772, baggage.getVolume());
        try {
            new Baggage(27, 10, 14, 60); //length > width, but width < height
        } catch (WrongBaggageSizeFormatException e) {
            assertEquals("Wrong Baggage size format. \nPlease note that length >= width >= height.", e.getMessage());
        }

    }
}
