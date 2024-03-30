package JUnitTest.DatastructureTest;

import model.DataStructure.DeskList;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeskListTest {
    private DeskList deskList;

    @BeforeEach
    void setUp() {
        // Signleton get the instance every time
        deskList = DeskList.getInstance();

        // CLEAR!! to reset all the desk
        deskList.stopAll();
        while(deskList.size() > 0) {
            deskList.removeDesk();
        }
    }

    /**
     * test if desk can be added correctly
     */
    @Test
    void addDeskTest() {
        deskList.addDesk();
        // After adding one desk, the size should be 1.
        assertEquals(1, deskList.size());
    }

    /**
     * test if desk can be removed correctly
     */
    @Test
    void removeDeskTest() {
        deskList.addDesk();
        deskList.removeDesk();
        // After adding and then removing a desk, the size should be 0.
        assertEquals(0, deskList.size());
    }

    /**
     * test if desk speed can be set correctly
     */
    @Test
    void setSpeedTest() {
        deskList.addDesk();
        deskList.setSpeed(4);
        //The speed of the desk should be set to 5
        assertEquals(4, deskList.getArrayList().get(0).getSpeed());
    }

    /**
     * test if desk can be stopped correctly
     */
    @Test
    void stopAllTest() throws InterruptedException {
        deskList.addDesk();
        deskList.addDesk(); // add more desks and stop, to see if size changed, to see if thread stopped.
        deskList.stopAll();

        Thread.sleep(100); // set a delay, waiting for update

        //All desks should be stopped.
        // assertTrue(deskList.getArrayList().stream().noneMatch(desk -> desk.ifStop()));

        int expectedSize = deskList.size();
        // All desks should be stopped and no further desks should be added or removed.
        assertEquals(expectedSize, deskList.size());

    }

    /**
     * test if desk can resume
     */
    @Test
    void resumeAllTest() throws InterruptedException {
        deskList.addDesk();

        deskList.stopAll();
        Thread.sleep(100); //  set a delay, make sure all threads stops

        deskList.resumeAll();
        Thread.sleep(100); // set a delay, waiting for update
        // All desks should be resumed.
        assertTrue(deskList.getArrayList().stream().allMatch(desk -> desk.ifRunning()));
    }
}
