package views.Panels;

import interfaces.Observer;
import model.Algorithm.LogGenerator;
import model.DataStructure.*;
import views.Frames.ProgramGUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static views.Frames.ProgramGUI.titleFont;

public class WaitingQueuePanel extends JPanel {
    private static final Font subtitleFront = new Font("Arial", Font.BOLD, 18);
    private static final WaitingQueuePanel instance = new WaitingQueuePanel();
    private final PassengerList[] waitingQueues = new PassengerList[2]; // 0: economic class, 1: business class

    public WaitingQueuePanel() {
        for (int i = 0; i < waitingQueues.length; i++) {
            waitingQueues[i] = new PassengerList();
        }

        TitledBorder waitingQueueBorder = BorderFactory.createTitledBorder(null, "Waiting Queue", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont);
        setBorder(waitingQueueBorder);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        QueuePanel economicClassPanel = new QueuePanel(waitingQueues[0], "Economy Class");
        QueuePanel businessClassPanel = new QueuePanel(waitingQueues[1], "Business Class");
        add(economicClassPanel);
        add(businessClassPanel);
    }

    public static WaitingQueuePanel getInstance() {
        return instance;
    }
    public PassengerList[] getWaitingQueues() {
        return waitingQueues;
    }

    public void appendWaitingQueues(PassengerList passengerList) {
        for (Passenger p : passengerList.getPassengerList()) {
            p.setIdx(ProgramGUI.getIdx3().getAndIncrement());
            // A passenger has 20% chance to go to the business class and 80% chance to go to the economic class
            if (Math.random() < 0.2) {
                waitingQueues[1].add(p);
            } else {
                waitingQueues[0].add(p);
            }
            String message = String.format("No.%-4d %s: %s %s has been added to the waiting queue at %s.\n", p.getIdx(), p.getReferenceCode(), p.getFirstName(), p.getLastName(), EventBoardPanel.getVirtualTime());
            LogGenerator.getInstance().addLog(message);
        }
    }

    private class QueuePanel extends JPanel implements Observer {
        private final JLabel label;
        private final JTextArea queueTextArea;
        private final PassengerList waitingQueue;

        public QueuePanel(PassengerList waitingQueue, String title) {
            this.waitingQueue = waitingQueue;
            waitingQueue.registerObserver(this); // register as an observer
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, subtitleFront));
            label = new JLabel("   There are currently 0 passenger(s) waiting...      ");
            label.setFont(subtitleFront);
            label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            add(label);
            queueTextArea = new JTextArea();
            queueTextArea.setFont(new Font("Monospaced", Font.BOLD, 16));
            queueTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(queueTextArea);
            add(scrollPane);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }

        @Override
        public void update() {
            // update label
            label.setText(String.format("   There are currently %d passenger(s) waiting...   ", waitingQueue.size()));
            // update TextArea
            String s = waitingQueue.toString();
            synchronized (queueTextArea) {
                queueTextArea.setText(s);
            }
        }
    }

}
