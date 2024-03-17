package views.Panels;

import interfaces.Observer;
import model.Algorithm.LogGenerator;
import model.Algorithm.PassengerGenerator;
import model.DataStructure.*;
import views.ProgramGUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static views.ProgramGUI.titleFont;

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
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        label = new JLabel("   There are currently 0 passenger(s) waiting...      ");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        add(label, gbc);

        queueTextArea = new JTextArea();
        queueTextArea.setFont(new Font("Monospaced", Font.BOLD, 17));
//        queueTextArea.setText(String.format("No.1 %-10s %-9s %-10s %s\n", PassengerGenerator.generateRandomReferenceCode(), PassengerGenerator.generateRandomName(), PassengerGenerator.generateRandomName(), PassengerGenerator.generateRandomBaggage().printBaggage()));
        queueTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(queueTextArea);
        gbc.weighty = 1;
//        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
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
            String message = String.format("No.%-4d %s: %s %s has been added to the waiting queue.", p.getIdx(), p.getReferenceCode(), p.getFirstName(), p.getLastName());
            LogGenerator.getInstance().addLog(message);

        }
    }
    @Override
    public synchronized void update() {
        // update TextArea
        StringBuilder sb = new StringBuilder();
        List<Passenger> copyList = new ArrayList<>(waitingQueue.getPassengerList());
        for(Passenger p : copyList) {
            sb.append(String.format("No.%-4d %-10s %-9s %-10s %s\n", p.getIdx(), p.getReferenceCode(), p.getFirstName(), p.getLastName(), p.getBaggage().printBaggage()));
        }
        queueTextArea.setText(sb.toString());
        // update label
        label.setText(String.format("   There are currently %-3d passenger(s) waiting...   ", waitingQueue.size()));
    }
}
