package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class FlightInquiryPanel extends JPanel{

    public FlightInquiryPanel() {
        setLayout(new BorderLayout());
    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setTitle("Airport System");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width - 400) / 2;
        int y = (height - 400) / 2;
        testFrame.setLocation(x, y);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(400, 400);
        testFrame.add(new FlightInquiryPanel());
        testFrame.setVisible(true);
        testFrame.setResizable(false);
    }

}
