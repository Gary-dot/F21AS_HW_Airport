package GUI.Panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static GUI.ProgramGUI.titleFont;

public class DeskDetailsPanel extends JPanel {
    public DeskDetailsPanel() {
        setBorder(BorderFactory.createTitledBorder(null, "Desk Details", TitledBorder.CENTER, TitledBorder.BELOW_TOP, titleFont));
        setLayout(new GridLayout(2, 1));
        JLabel desk1Label = new JLabel("Desk 1: Wilson William(23x18x10 27) is checking in. No baggage fee is charged.");
        JLabel desk2Label = new JLabel("Desk 2: Garcia Joseph(23x14x11 40) is checking in. A baggage fee of 20£ is charged.");
        add(desk1Label);
        add(desk2Label);
    }
}
