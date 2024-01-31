package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    /**
     * The panel for the Welcome page.
     */
    private JButton checkInButton;
    private JButton flightInquiryButton;

    public JButton getCheckInButton() {
        return checkInButton;
    }

    public JButton getFlightInquiryButton() {
        return flightInquiryButton;
    }

    public WelcomePanel() {

        JLabel welcomeLabel = new JLabel("Welcome to the Airport System!");
        welcomeLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        checkInButton = new JButton("   Check In   ");
        checkInButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        checkInButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        flightInquiryButton = new JButton("Flight Inquiry");
        flightInquiryButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        flightInquiryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));
        add(welcomeLabel);
        add(Box.createVerticalStrut(50));
        add(checkInButton);
        add(Box.createVerticalStrut(50));
        add(flightInquiryButton);
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
        testFrame.add(new WelcomePanel());
        testFrame.setVisible(true);
        testFrame.setResizable(false);
    }
}
