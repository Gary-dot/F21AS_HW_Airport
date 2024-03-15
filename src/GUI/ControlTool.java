package GUI;

import javax.swing.*;
import java.awt.*;
public class ControlTool extends JFrame {
    private JButton clearPassengerButton;
    private JButton addPassengerButton;
    private JButton addDeskButton;
    private JButton deleteDeskButton;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    public ControlTool() {
        setTitle("Control Tool");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Font font = new Font("Arial", Font.BOLD, 20);
        JPanel p = (JPanel) getContentPane();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(4, 1,10,10));
        clearPassengerButton = new JButton("Clear all passengers");
        clearPassengerButton.setFont(font);
        addPassengerButton = new JButton("Add a random passenger");
        addPassengerButton.setFont(font);
        addDeskButton = new JButton("Add a desk");
        addDeskButton.setFont(font);
        deleteDeskButton = new JButton("Delete a desk");
        deleteDeskButton.setFont(font);
        p.add(clearPassengerButton);
        p.add(addPassengerButton);
        p.add(addDeskButton);
        p.add(deleteDeskButton);
        setResizable(false);
//        setVisible(true);
    }
    public static void main(String[] args) {
        new ControlTool();
    }
}
