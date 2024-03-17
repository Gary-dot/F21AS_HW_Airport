package views.Panels;

import interfaces.Observer;
import model.DataStructure.FlightDetails;
import model.DataStructure.FlightDetailsList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static views.ProgramGUI.titleFont;

/**
 * Observer pattern: FlightDetailsPanel is an observer of FlightDetailsList.
 */
public class FlightDetailsPanel extends JPanel implements Observer {
    private static final FlightDetailsPanel instance = new FlightDetailsPanel();
    private final JPanel flightDetailsPanel;
    class FlightDetailsComponent extends JPanel implements Observer{
        private final JLabel flightTextLabel;
        public FlightDetailsComponent(FlightDetails flightDetails) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if(flightDetails == null) {
                flightTextLabel = new JLabel("No flight details available");
                add(flightTextLabel);
                return;
            }
            flightDetails.registerObserver(this); // register as an observer
            flightTextLabel = new JLabel(flightDetails.toString());
            flightTextLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
            add(flightTextLabel);
            this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));//limit size to one row in scrollpanel
            this.setToolTipText(String.format("The excess fee for this flight is: £%d", flightDetails.getTotalExcessFee()));
        }
        @Override
        public void update() {
            flightTextLabel.setText(FlightDetailsList.getInstance().toString());
        }
    }

    private FlightDetailsPanel() {
        FlightDetailsList.getInstance().registerObserver(this); // register as an observer

        setBorder(BorderFactory.createTitledBorder(null, "Flight Details", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JLabel label = new JLabel("     Flight(Destination)    Check-ins/All     Weight      Volume      ");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, gbc);

        flightDetailsPanel = new JPanel();
        flightDetailsPanel.setLayout(new BoxLayout(flightDetailsPanel, BoxLayout.PAGE_AXIS)); // set layout
        JScrollPane scrollPane = new JScrollPane(flightDetailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//        flightDetailsTextArea = new JTextArea();
//        flightDetailsTextArea.setFont(new Font("Monospaced", Font.BOLD, 18));
//        flightDetailsTextArea.setText(String.format(" %-22s%-11s%-9s%s\n", "Flight(Destination)", "20/115", "10.1%", "%20.1%"));
//        flightDetailsTextArea.setEditable(false);
        gbc.weighty = 1;
//        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
    }

    public static FlightDetailsPanel getInstance() {
        return instance;
    }
    @Override
    public synchronized void update() {
        flightDetailsPanel.removeAll();
        for (FlightDetails flightDetails : FlightDetailsList.getInstance().getFlightDetailsList()) {
            FlightDetailsComponent flightDetailsComponent = new FlightDetailsComponent(flightDetails);
            flightDetailsPanel.add(flightDetailsComponent);
        }
        flightDetailsPanel.revalidate();
        flightDetailsPanel.repaint();
    }
}
