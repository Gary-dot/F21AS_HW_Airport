package views.Panels;

import interfaces.Observer;
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

    private final JTextArea flightDetailsTextArea;

    private FlightDetailsPanel() {
        setBorder(BorderFactory.createTitledBorder(null, "Flight Details", TitledBorder.CENTER, TitledBorder.BELOW_TOP, titleFont));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JLabel label = new JLabel("     Flight(Destination)    Check-ins/All     Weight      Volume      ");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, gbc);

        flightDetailsTextArea = new JTextArea();
        flightDetailsTextArea.setFont(new Font("Monospaced", Font.BOLD, 18));
//        flightDetailsTextArea.setText(String.format(" %-22s%-11s%-9s%s\n", "Flight(Destination)", "20/115", "10.1%", "%20.1%"));
        flightDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(flightDetailsTextArea);
        gbc.weighty = 1;
//        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        FlightDetailsList.getInstance().registerObserver(this);
    }

    public static FlightDetailsPanel getInstance() {
        return instance;
    }
    @Override
    public synchronized void update() {
        flightDetailsTextArea.setText(FlightDetailsList.getInstance().toString());
    }
}
