package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    public TestFrame(JPanel panel) {
        setTitle("Airport System");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width - 400) / 2;
        int y = (height - 400) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        add(panel);
        setResizable(false);
    }
}
