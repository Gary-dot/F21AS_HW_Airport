package views.Panels;

import interfaces.Observer;
import model.Algorithm.LogGenerator;
import model.DataStructure.*;
import views.Frames.ProgramGUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static views.Frames.ProgramGUI.titleFont;

public class WaitingQueuePanel extends JPanel implements Observer {
    private static final WaitingQueuePanel instance = new WaitingQueuePanel();
    public static WaitingQueuePanel getInstance() {
        return instance;
    }
    private final JTextArea queueTextArea;
    private final JLabel label;

    private final PassengerList waitingQueue = new PassengerList();

    public WaitingQueuePanel() {
        waitingQueue.registerObserver(this); // register as an observer

        TitledBorder waitingQueueBorder = BorderFactory.createTitledBorder(null, "Waiting Queue", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont);
        setBorder(waitingQueueBorder);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        label = new JLabel("   There are currently 0 passenger(s) waiting...      ");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        add(label);

        queueTextArea = new JTextArea();
        queueTextArea.setFont(new Font("Monospaced", Font.BOLD, 16));
//        queueTextArea.setText(String.format("No.1 %-10s %-9s %-10s %s\n", PassengerGenerator.generateRandomReferenceCode(), PassengerGenerator.generateRandomName(), PassengerGenerator.generateRandomName(), PassengerGenerator.generateRandomBaggage().printBaggage()));
        queueTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(queueTextArea);
//        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane);
    }
    public PassengerList getWaitingQueue() {
        return waitingQueue;
    }
    public void appendWaitingQueue(PassengerList passengerList) {
        for (Passenger p : passengerList.getPassengerList()) {
            synchronized (waitingQueue) { // synchronize the access to the waitingQueue
                p.setIdx(ProgramGUI.getIdx3().getAndIncrement());
                waitingQueue.add(p);
            }
            String message = String.format("No.%-4d %s: %s %s has been added to the waiting queue at %s.\n", p.getIdx(), p.getReferenceCode(), p.getFirstName(), p.getLastName(), EventBoardPanel.getVirtualTime());
            LogGenerator.getInstance().addLog(message);

        }
    }
    @Override
    public void update() {
        // update label
        label.setText(String.format("   There are currently %-3d passenger(s) waiting...   ", waitingQueue.size()));
        // update TextArea
        String s = waitingQueue.toString();
        synchronized (queueTextArea) {
            queueTextArea.setText(s);
        }
    }
}
