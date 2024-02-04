package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class BaggageNoticePanel extends JPanel {
    private JButton nextButton;
    private JButton quitButton;

    public BaggageNoticePanel() {
        setLayout(null);
        JLabel notice = new JLabel("<html>You will then enter your baggage details,<br>" +
                "including length, width, height, and weight.<br>" +
                "The length, width, height are in inches.<br>" +
                "The weight is in pounds.<br><br>" +
                "Please note that length >= width >= height.<br><br>" +
                "If you exceed the baggage limit, <br>" +
                "you will be charged an extra fee.<br>" +
                "</html>");
        notice.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        notice.setBounds(20, 0, 400, 300);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        nextButton.setBounds(210, 300, 180, 60);
        quitButton = new JButton("Back");
        quitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        quitButton.setBounds(0, 300, 180, 60);

        add(notice);
        add(nextButton);
        add(quitButton);
    }

    public static void main(String[] args) {
        new TestFrame(new BaggageNoticePanel()).setVisible(true);
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
