package JUnitTest.DatastructureTest;

import model.DataStructure.FlightDetails;
import model.DataStructure.FlightDetailsList;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightDetailsListTest {

    // private FlightDetailsList fdl = new FlightDetailsList();
    private FlightDetailsList fdl;

    @BeforeEach
    void setUp() {
        fdl = FlightDetailsList.getInstance();
        fdl.getFlightDetailsList().clear(); // Clean up the singleton instance so that each test starts with a clean state
    }

    /**
     * test if details can be added correctly
     */
    @Test
    public void addDetailsTest() {
        FlightDetails fd = new FlightDetails("CA890");
        fdl.addDetails(fd);
        // insure can add flight correctly
        assertEquals(1, fdl.size(), "Flight should be added.");

        FlightDetails fd2 = new FlightDetails("CA890");
        fdl.addDetails(fd2);
        // insure flight will not be added repeat
        assertEquals(1, fdl.size(), "SHould not add repeat");
    }

    @Test
    public void removeDetailsTest(){
        FlightDetails fd3 = new FlightDetails("MH888");
        fdl.addDetails(fd3);
        assertEquals(1,fdl.size());

        // insure will not remove flight wrongly
        fdl.removeDetails("CA234");
        assertEquals(1,fdl.size());

        // insure can remove flight correctly
        fdl.removeDetails("MH888");
        assertEquals(0,fdl.size());
    }
}
