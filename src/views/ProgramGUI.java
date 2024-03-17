package views;

import controllers.*;
import views.Panels.*;

import java.awt.*;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class ProgramGUI extends JFrame {
    private DeskDetailsPanel deskDetailsPanel;
    private FlightDetailsPanel flightDetailsPanel;
    private EventBoardPanel eventBoardPanel;
    private WaitingQueuePanel waitingQueuePanel;
    public static int CenterX;
    public static int CenterY;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;
    public static final Font titleFont = new Font("Arial", Font.BOLD, 20);
    private static final AtomicInteger idx3 = new AtomicInteger(1);
    public static AtomicInteger getIdx3() {
        return idx3;
    }

    public ProgramGUI() {
        // Create the main frame
        setTitle("Airport Check-In System");
        setLayout(new GridLayout(2,2)); // Divide the frame into 4 sections
        // Create panels
        eventBoardPanel = EventBoardPanel.getInstance();
        waitingQueuePanel = WaitingQueuePanel.getInstance();
        deskDetailsPanel = new DeskDetailsPanel();
        flightDetailsPanel = FlightDetailsPanel.getInstance();

        // Add controllers to panels
        new EventBoardController(eventBoardPanel);

        // Add panels to frame
        add(flightDetailsPanel);
        add(eventBoardPanel);

        add(waitingQueuePanel);
        add(deskDetailsPanel);

        // Pack and set visible
        pack();
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT); // You can adjust the size as needed
        setVisible(true);
//        setResizable(false);

        // Set Location
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        CenterX = (width - SCREEN_WIDTH) / 2;
        CenterY = (height - SCREEN_HEIGHT) / 2;
        setLocation(CenterX, CenterY);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.UK);
        new ProgramGUI();
    }
}
