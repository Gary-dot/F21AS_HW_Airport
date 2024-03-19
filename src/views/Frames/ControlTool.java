package GUI.Frames;

import DataStructure.Passenger;
import DataStructure.PassengerList;
import GUI.Panels.DeskDetailsPanel;
import GUI.Panels.EventBoardPanel;
import GUI.Panels.WaitingQueuePanel;
import GUI.ProgramGUI;
import Main.AirportSystem;

import javax.swing.*;
import java.awt.*;
public class ControlTool extends JFrame {
    private JButton clearPassengerButton;
    private JButton addPassengerButton;
    private JButton addDeskButton;
    private JButton deleteDeskButton;
    public static final int TOOL_WIDTH = 320;
    public static final int TOOL_HEIGHT = 200;

    public ControlTool() {
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
        addDeskButton = new JButton("Add a desk");
        addDeskButton.setFont(font);
        deleteDeskButton = new JButton("Delete a desk");
        deleteDeskButton.setFont(font);
        panel.add(clearPassengerButton);
        panel.add(addPassengerButton);
        panel.add(addDeskButton);
        panel.add(deleteDeskButton);

        WaitingQueuePanel waitingQueuePanel = ProgramGUI.getWaitingQueuePanel();
        PassengerList wl = WaitingQueuePanel.getWaitingQueue();
        InfoDisplay infoDisplay = ProgramGUI.getInfoDisplay();
        DeskDetailsPanel deskDetailsPanel = ProgramGUI.getDeskDetailsPanel();

        clearPassengerButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all passengers?", "Clear all passengers", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                for(Passenger p: wl.getPassengerList()){
                    String text = String.format("No.%-4d %s: Failed to check in at %s!\nYou removed this passenger!", p.getIdx(), p.getFlightCode(), EventBoardPanel.getVirtualTime());
                    infoDisplay.appendText(text);
                    AirportSystem.log(text);
                }
                // clear all passengers
                waitingQueuePanel.clearWaitingQueue();
            }
        });

        addPassengerButton.addActionListener(e -> {
            waitingQueuePanel.addWaitingQueue();
            waitingQueuePanel.updateText();
        });

        addDeskButton.addActionListener(e -> {
            ProgramGUI.getDeskList().addDesk();
            deskDetailsPanel.updateText();
        });

        deleteDeskButton.addActionListener(e -> {
            ProgramGUI.getDeskList().removeDesk();
            deskDetailsPanel.updateText();
        });

        setResizable(false);
    }
}
