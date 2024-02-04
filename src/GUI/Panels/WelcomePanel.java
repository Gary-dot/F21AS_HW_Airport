package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    /**
     * The panel for the Welcome page.
     */
    private JButton checkInButton;
    private JButton flightInquiryButton;

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
        new TestFrame(new WelcomePanel()).setVisible(true);
    }

    public JButton getCheckInButton() {
        return checkInButton;
    }

    public JButton getFlightInquiryButton() {
        return flightInquiryButton;
    }
}
