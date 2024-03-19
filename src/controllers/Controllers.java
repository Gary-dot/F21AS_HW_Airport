package controllers;

import model.Algorithm.LogGenerator;
import model.Algorithm.PassengerGenerator;
import model.DataStructure.DeskList;
import views.Frames.ControlTool;
import views.Panels.ControlPanel;
import views.Panels.EventBoardPanel;

import javax.swing.*;

public class Controllers {
    public static boolean isRunning() {
        return running;
    }

    private static boolean running = true;

    public Controllers() {
        // Get some variables from EventBoardPanel
        ControlPanel controlPanel = ControlPanel.getInstance();
        JButton speedButton = controlPanel.getSpeedButton();
        JButton pauseButton = controlPanel.getPauseButton();
        JButton exitButton = controlPanel.getExitButton();
        JButton toolButton = controlPanel.getToolButton();
        DeskList deskList = DeskList.getInstance();

        javax.swing.Timer timer = EventBoardPanel.getInstance().getTimer();

        // Add action listeners to the buttons
        speedButton.addActionListener(e -> {
            if (speedButton.getText().equals("  x1  ")) {
                speedButton.setText("  x2  ");
                timer.setDelay(500);
                deskList.setSpeed(2);
                PassengerGenerator.getInstance().setDelay(500);
            } else if (speedButton.getText().equals("  x2  ")) {
                speedButton.setText("  x4  ");
                timer.setDelay(250);
                deskList.setSpeed(4);
                PassengerGenerator.getInstance().setDelay(250);
            } else if (speedButton.getText().equals("  x4  ")) {
                speedButton.setText("  x8  ");
                timer.setDelay(125);
                deskList.setSpeed(8);
                PassengerGenerator.getInstance().setDelay(125);
            } else {
                speedButton.setText("  x1  ");
                timer.setDelay(1000);
                deskList.setSpeed(1);
                PassengerGenerator.getInstance().setDelay(1000);
            }
        });

        pauseButton.addActionListener(e -> {
            if (pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Resume");
                timer.stop();
                PassengerGenerator.getInstance().stop();
                deskList.stopAll();
            } else {
                pauseButton.setText("Pause");
                timer.start();
                PassengerGenerator.getInstance().resume();
                deskList.resumeAll();
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

        toolButton.addActionListener(e -> {
            if (ControlTool.getInstance().isVisible()) {
                ControlTool.getInstance().setVisible(false);
            } else {
                ControlTool.getInstance().setLocation(100, 100);
                ControlTool.getInstance().setVisible(true);
            }
        });

    }
}
