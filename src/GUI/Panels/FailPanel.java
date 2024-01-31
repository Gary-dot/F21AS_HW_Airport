package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class FailPanel extends JPanel {
    private JButton QuitButton;
    private JButton ReenterButton;

    public JButton getQuitButton() {
        return QuitButton;
    }
    public JButton getReenterButton() {
        return ReenterButton;
    }
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
        testFrame.add(new FailPanel());
        testFrame.setVisible(true);
        testFrame.setResizable(false);
    }
}
