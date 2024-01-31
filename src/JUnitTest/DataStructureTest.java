package JUnitTest;

import DataStructure.Baggage;
import DataStructure.Exceptions.WrongBaggageSizeFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
