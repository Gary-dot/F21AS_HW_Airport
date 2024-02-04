package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class AlreadyCheckedInPanel extends JPanel {
    private JButton QuitButton;

    public AlreadyCheckedInPanel() {
        setLayout(null);
        JLabel successLabel = new JLabel("You have already checked in!");
        successLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        successLabel.setBounds(40, 60, 350, 60);

        QuitButton = new JButton("Quit");
        QuitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        QuitButton.setBounds(45, 200, 300, 100);
        add(successLabel);
        add(QuitButton);
    }

    public static void main(String[] args) {
        new TestFrame(new AlreadyCheckedInPanel()).setVisible(true);
    }

    public JButton getQuitButton() {
        return QuitButton;
    }
}
