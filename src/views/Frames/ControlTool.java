package views.Frames;



import model.Algorithm.LogGenerator;
import model.DataStructure.DeskList;
import model.DataStructure.Passenger;
import model.DataStructure.PassengerList;
import views.Panels.DeskDetailsPanel;
import views.Panels.EventBoardPanel;
import views.Panels.WaitingQueuePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlTool extends JFrame {
    private static ControlTool instance;
    public static ControlTool getInstance() {
        if (instance == null) {
            instance = new ControlTool();
        }
        return instance;
    }
    private JButton clearPassengerButton;
    private JButton addPassengerButton;
    private JButton addDeskButton;
    private JButton deleteDeskButton;
    public static final int TOOL_WIDTH = 320;
    public static final int TOOL_HEIGHT = 200;

    private ControlTool() {
        setTitle("Control Tool");
        setSize(TOOL_WIDTH, TOOL_HEIGHT);
        Font font = new Font("Arial", Font.BOLD, 20);
        JPanel panel = (JPanel) getContentPane();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(4, 1,10,10));
        clearPassengerButton = new JButton("Clear all passengers");
        clearPassengerButton.setFont(font);
        addPassengerButton = new JButton("Spawn a random passenger");
        addPassengerButton.setFont(font);
        addDeskButton = new JButton("Add a counter");
        addDeskButton.setFont(font);
        deleteDeskButton = new JButton("Delete a counter");
        deleteDeskButton.setFont(font);
        panel.add(clearPassengerButton);
        panel.add(addPassengerButton);
        panel.add(addDeskButton);
        panel.add(deleteDeskButton);

        WaitingQueuePanel waitingQueuePanel = WaitingQueuePanel.getInstance();
        PassengerList[] passengerLists = waitingQueuePanel.getWaitingQueues();

        clearPassengerButton.addActionListener((e) -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all passengers?", "Clear all passengers", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                // For all passengers in both waiting queues, generate a log and remove them
                for(PassengerList wl: passengerLists){
                    for(Passenger p: wl.getPassengerList()){
                        String text = String.format("No.%-4d %s: Failed to check in at %s!\nYou removed this passenger!", p.getIdx(), p.getFlightCode(), EventBoardPanel.getVirtualTime());
                        LogGenerator.getInstance().addLog(text);
                    }
                    // clear all passengers
                    wl.clear();
                }
            }
        });

        addPassengerButton.addActionListener(e -> {
            // A random passenger will have 20% chance to go to the business class and 80% chance to go to the economic class
            PassengerList wl = passengerLists[(Math.random() < 0.2) ? 1 : 0];
            wl.addARandomPassenger();
        });

        addDeskButton.addActionListener(e -> {
            DeskList.getInstance().addDesk();
        });

        deleteDeskButton.addActionListener(e -> {
            DeskList.getInstance().removeDesk();
        });
        setResizable(false);
    }
}
