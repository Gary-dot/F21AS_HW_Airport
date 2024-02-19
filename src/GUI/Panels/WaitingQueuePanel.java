package GUI.Panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static GUI.ProgramGUI.titleFont;

public class WaitingQueuePanel extends JPanel {
    public WaitingQueuePanel() {
        TitledBorder waitingQueueBorder = BorderFactory.createTitledBorder(null, "Waiting Queue", TitledBorder.CENTER, TitledBorder.BELOW_TOP, titleFont);
        setBorder(waitingQueueBorder);
        setLayout(new BorderLayout());
        JTextArea waitingQueueTextArea = new JTextArea("There are currently 11 people waiting in the queue:\nNo.5 ORD7557 Johnson John 23x18x10(in) 28lb\nNo.6......");
        add(waitingQueueTextArea, BorderLayout.CENTER);
    }
}
