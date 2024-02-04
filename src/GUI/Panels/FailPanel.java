package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class FailPanel extends JPanel {
    private JButton QuitButton;
    private JButton ReenterButton;

    public FailPanel() {
        setLayout(null);
        JLabel successLabel = new JLabel("Wrong Reference Number or Lastname!");
        successLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        successLabel.setBounds(20, 30, 350, 100);

        QuitButton = new JButton("Quit");
        QuitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        QuitButton.setBounds(0, 250, 180, 60);
        ReenterButton = new JButton("Re-enter");
        ReenterButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        ReenterButton.setBounds(210, 250, 180, 60);
        add(successLabel);
        add(ReenterButton);
        add(QuitButton);

    }

    public static void main(String[] args) {
        new TestFrame(new FailPanel()).setVisible(true);
    }

    public JButton getQuitButton() {
        return QuitButton;
    }

    public JButton getReenterButton() {
        return ReenterButton;
    }
}
