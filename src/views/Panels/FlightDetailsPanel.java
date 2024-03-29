package views.Panels;

import interfaces.Observer;
import model.DataStructure.FlightDetails;
import model.DataStructure.FlightDetailsList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

import static views.Frames.ProgramGUI.titleFont;

/**
 * Observer pattern: FlightDetailsPanel is an observer of FlightDetailsList.
 */
public class FlightDetailsPanel extends JPanel implements Observer {
    private static final FlightDetailsPanel instance = new FlightDetailsPanel();
    private final JPanel flightDetailsPanel;
    private FlightDetailsPanel() {
        flightDetailsComponents = new ArrayList<>();
        FlightDetailsList.getInstance().registerObserver(this); // register as an observer
        setBorder(BorderFactory.createTitledBorder(null, "Flight Details", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel(" Flight(Destination)     Check-ins/All      Weight      Volume");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        add(label);
        flightDetailsPanel = new JPanel();
        flightDetailsPanel.setLayout(new BoxLayout(flightDetailsPanel, BoxLayout.PAGE_AXIS)); // set layout
        JScrollPane scrollPane = new JScrollPane(flightDetailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    public static FlightDetailsPanel getInstance() {
        return instance;
    }
    @Override
    public synchronized void update() {
        // update the flight details panel
        FlightDetailsList flightDetailsList = FlightDetailsList.getInstance();
        if(flightDetailsComponents.size() + 1 == flightDetailsList.getFlightDetailsList().size()) { // if a new flight is added
            FlightDetails flightDetails = flightDetailsList.getFlightDetailsList().getLast();
            FlightDetailsComponent flightDetailsComponent = new FlightDetailsComponent(flightDetails);
            flightDetailsPanel.add(flightDetailsComponent);
            flightDetailsComponents.add(flightDetailsComponent);
        } else if(flightDetailsComponents.size() - 1 == flightDetailsList.getFlightDetailsList().size()) { // if a flight is removed
            flightDetailsPanel.remove(0);
            flightDetailsComponents.removeFirst();
        }
        flightDetailsPanel.revalidate();
        flightDetailsPanel.repaint();
    }

    private ArrayList<FlightDetailsComponent> flightDetailsComponents;

    private class FlightDetailsComponent extends JPanel implements Observer {
        private final JLabel flightLabel;
        private final FlightDetails flightDetails;
        public FlightDetailsComponent(FlightDetails flightDetails) {
            this.flightDetails = flightDetails;
            flightDetails.registerObserver(this); // register as an observer
            // set layout
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // add label
            flightLabel = new JLabel(flightDetails.toString());
            flightLabel.setFont(new Font("Monospaced", Font.BOLD, 17));
            add(flightLabel);
            // set maximum size
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));//limit size to one row in scrollpanel
            // set tool tip text
            setToolTipText(String.format("The excess fee for this flight is: £%d", flightDetails.getTotalExcessFee()));
        }
        @Override
        public void update() {
            flightLabel.setText(flightDetails.toString());
            this.setToolTipText(String.format("The excess fee for this flight is: £%d", flightDetails.getTotalExcessFee()));
        }
    }
}
