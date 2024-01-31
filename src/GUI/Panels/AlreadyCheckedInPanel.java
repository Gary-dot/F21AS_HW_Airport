package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class AlreadyCheckedInPanel extends JPanel{
    private JButton QuitButton;
    public JButton getQuitButton() {
        return QuitButton;
    }
    public AlreadyCheckedInPanel() {
        setLayout(null);
        JLabel successLabel = new JLabel("You cannot check in twice!");
        successLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        successLabel.setBounds(40, 60, 350, 60);

        QuitButton = new JButton("Quit");
        QuitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        QuitButton.setBounds(45, 200, 300, 100);
        add(successLabel);
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
        testFrame.add(new AlreadyCheckedInPanel());
        testFrame.setVisible(true);
        testFrame.setResizable(false);
    }
}
