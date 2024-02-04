package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class SuccessPanel extends JPanel {
    /**
     * The panel for the Success page.
     */
    private JButton QuitButton;
    public JButton getQuitButton() {
        return QuitButton;
    }
    public SuccessPanel() {
        setLayout(null);
        JLabel successLabel = new JLabel("You have been successfully checked in!");
        successLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        successLabel.setBounds(10, 30, 400, 100);

        QuitButton = new JButton("Quit");
        QuitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        QuitButton.setBounds(45, 200, 300, 100);
        add(successLabel);
        add(QuitButton);

    }

    public static void main(String[] args) {
        new TestFrame(new SuccessPanel()).setVisible(true);
    }
}
