package GUI.Panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static GUI.ProgramGUI.titleFont;

public class FlightDetailsPanel extends JPanel {
    public FlightDetailsPanel() {
        setBorder(BorderFactory.createTitledBorder(null, "Flight Details", TitledBorder.CENTER, TitledBorder.BELOW_TOP, titleFont));
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Flight(Destination)"));
        add(new JLabel("CA890(Beijing)"));
        add(new JLabel("Check-ins/All"));
        add(new JLabel("10/50"));
        add(new JLabel("Weight"));
        add(new JLabel("5.1%"));
        add(new JLabel("Volume"));
        add(new JLabel("10.2%"));
    }
}
