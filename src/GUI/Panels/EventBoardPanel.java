package GUI.Panels;

import GUI.ControlTool;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static GUI.ProgramGUI.*;

public class EventBoardPanel extends JPanel {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private ControlTool controlTool;
    LocalTime virtualTime = LocalTime.MIDNIGHT; // Start at midnight
    JButton speedButton;
    JButton pauseButton;
    JButton toolButton;

    public EventBoardPanel() {
        setBorder(BorderFactory.createTitledBorder(null, "Event Board", TitledBorder.CENTER, TitledBorder.BELOW_TOP, titleFont));
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        controlTool = new ControlTool();
        speedButton = new JButton("  x1  ");
        pauseButton = new JButton("Pause");
        toolButton = new JButton("Tools");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JLabel t1 = new JLabel("(hh:mm)");
        t1.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font size of the title
        JLabel timeLabel = new JLabel(virtualTime.format(TIME_FORMATTER));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Set the font size of the clock
        add(timeLabel, gbc);
        add(t1, gbc);
        // Create a timer to update the time every second
        Timer timer = new Timer(1000, e -> {
            virtualTime = virtualTime.plusMinutes(1);
            timeLabel.setText(virtualTime.format(TIME_FORMATTER));
        });
        timer.start();
        JLabel emptyLabel = new JLabel("   ");
        add(emptyLabel, gbc);
        gbc.weightx = 1;
        JPanel buttonPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 3,5,5);
        buttonPanel.setLayout(gridLayout);
        buttonPanel.add(speedButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(toolButton);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(buttonPanel, gbc);

        gbc.gridx = 0; // Reset to first column
        gbc.gridy = 1; // Move to second row
        gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span across the remainder of the row
        gbc.fill = GridBagConstraints.BOTH; // Fill horizontally only
        JTextArea noticeBoardTextArea = new JTextArea("CA890(Beijing) Starts check-in at 06:00\nHU160(Shanghai) has departed at 6:20\n......");
        noticeBoardTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(noticeBoardTextArea);
        add(scrollPane, gbc);

        // Add action listeners to the buttons
        speedButton.addActionListener(e -> {
            if (speedButton.getText().equals("  x1  ")) {
                speedButton.setText("  x2  ");
                timer.setDelay(500);
            } else if(speedButton.getText().equals("  x2  ")) {
                speedButton.setText("  x4  ");
                timer.setDelay(250);
            } else if(speedButton.getText().equals("  x4  ")) {
                speedButton.setText("  x8  ");
                timer.setDelay(125);
            } else {
                speedButton.setText("  x1  ");
                timer.setDelay(1000);
            }
        });

        pauseButton.addActionListener(e -> {
            if (pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Resume");
                System.out.println("Resume button size: " + pauseButton.getSize()); // Debugging
                timer.stop();
            } else {
                pauseButton.setText("Pause");
                System.out.println("Pause button size: " + pauseButton.getSize()); // Debugging
                timer.start();
            }
        });

        toolButton.addActionListener(e -> {
            if (controlTool.isVisible()) {
                controlTool.setVisible(false);
            } else {
                controlTool.setLocation(CenterX + SCREEN_WIDTH - 30, CenterY );
                controlTool.setVisible(true);
            }
        });
    }
}
