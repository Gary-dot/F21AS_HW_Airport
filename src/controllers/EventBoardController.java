package controllers;

import model.Algorithm.LogGenerator;
import views.Panels.EventBoardPanel;

import javax.swing.*;

public class EventBoardController {
    public EventBoardController(EventBoardPanel eventBoard) {
        // Get some variables from EventBoardPanel
        final JButton speedButton = eventBoard.getSpeedButton();
        final JButton pauseButton = eventBoard.getPauseButton();
        final JButton exitButton = eventBoard.getExitButton();
        javax.swing.Timer timer = eventBoard.getTimer();

        // Add action listeners to the buttons
        speedButton.addActionListener(e -> {
            if (speedButton.getText().equals("  x1  ")) {
                speedButton.setText("  x2  ");
                timer.setDelay(500);

            } else if (speedButton.getText().equals("  x2  ")) {
                speedButton.setText("  x4  ");
                timer.setDelay(250);

            } else if (speedButton.getText().equals("  x4  ")) {
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
                timer.stop();
            } else {
                pauseButton.setText("Pause");
                timer.start();
            }
        });

        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                result = JOptionPane.showConfirmDialog(null, "Do you want to generate the log file?", "", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    LogGenerator.getInstance().generateLogFile();
                }
                System.exit(0);
            }
        });

    }
}
