package views.Panels;


import model.Algorithm.LogGenerator;
import model.Algorithm.PassengerGenerator;
import model.DataStructure.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static views.Frames.ProgramGUI.titleFont;

public class EventBoardPanel extends JPanel {
    private static LocalTime virtualTime = LocalTime.of(6, 0, 0); // Start at midnight
    private static final EventBoardPanel instance = new EventBoardPanel();
    private final JTextArea noticeBoardTextArea;
    private final JLabel timeLabel;
    private final Timer timer;
    private JButton toolButton;
    private EventBoardPanel() {
        //    private FlightDetailsList flightDetailsList;
        setBorder(BorderFactory.createTitledBorder(null, "Event Board", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel t1 = new JLabel("(hh:mm)");
        t1.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font size of the title
        timeLabel = new JLabel(virtualTime.toString());
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font size of the clock
        JPanel timePanel = new JPanel();
        timePanel.add(timeLabel);
        timePanel.add(t1);
        timePanel.setBorder(BorderFactory.createTitledBorder(null, "Timer", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont));
        timePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        add(timePanel);

        noticeBoardTextArea = new JTextArea();
        noticeBoardTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(noticeBoardTextArea);
        noticeBoardTextArea.setEditable(false);
        // Set minimum width of the scroll pane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.add(scrollPane);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        add(textPanel);

        // Get some variables
        ArrayList<UpcomingFlight> upcomingFlights = new ArrayList<>();
        FlightDetailsList flightDetailsList = FlightDetailsList.getInstance();
        DeskList deskList = DeskList.getInstance();

        ArrayList<Flight> flights = FlightList.getInstance().getArrayList();
        AtomicInteger idx1 = new AtomicInteger(0);
        AtomicInteger idx2 = new AtomicInteger(0);
        LogGenerator lg = LogGenerator.getInstance();
        WaitingQueuePanel waitingQueuePanel = WaitingQueuePanel.getInstance();

        // Always check in 2 hours before the flight
//        Flight firstFlight = flights.get(idx2.getAndIncrement());
//        upcomingFlights.add(new UpcomingFlight(firstFlight, firstFlight.getDepartureTime().minusHours(2)));
        for (Flight f : flights) {
            upcomingFlights.add(new UpcomingFlight(f, f.getDepartureTime().minusHours(2)));
        }
        noticeBoardTextArea.append("The airport is open!\n");
        // Create a timer to update the time every second
        timer = new Timer(1000, e -> {
            if (virtualTime.equals(LocalTime.of(11, 59, 0))) {
                noticeBoardTextArea.setText("");
                virtualTime = LocalTime.of(6, 0, 0);
                noticeBoardTextArea.append("The airport is open!\n");
            } else {
                virtualTime = virtualTime.plusMinutes(1);
            }
            timeLabel.setText(virtualTime.toString());
            UpcomingFlight uf = upcomingFlights.get(idx2.get());
            if (virtualTime.equals(uf.getCheckInTime())) {
                String flightCode = uf.getFlight().getFlightCode();
                String destination = uf.getFlight().getDestination();
                String s = String.format("Flight %s(%s) is ready for check-in at %s.\n", flightCode, destination, virtualTime.toString());
                noticeBoardTextArea.append(s);
                lg.addLog(s);
                FlightDetails fd = new FlightDetails(upcomingFlights.get(idx2.get()).getFlight());
                flightDetailsList.addDetails(fd);
                deskList.addDesk();
                PassengerList pl = PassengerGenerator.generatePassengers(fd);
                waitingQueuePanel.appendWaitingQueues(pl);

                if (idx2.incrementAndGet() == flights.size()) {
                    idx2.set(0);
                }
            }
            Flight f = flights.get(idx1.get());
            if (virtualTime.equals(f.getDepartureTime())) {
                String flightCode = f.getFlightCode();
                String destination = f.getDestination();
                String s = String.format("Flight %s(%s) is ready to depart at %s.\n", flightCode, destination, virtualTime.toString());
                noticeBoardTextArea.append(s);
                lg.addLog(s);
                flightDetailsList.removeFirst();
                deskList.removeDesk();
                if (flightDetailsList.size() == 0) {
                    noticeBoardTextArea.append("The airport is closed!\n");
                    // For the rest of the passengers in the waiting queues, they have missed the flight.
                    for(PassengerList pl : waitingQueuePanel.getWaitingQueues()) {
                        for (Passenger p : pl.getPassengerList()) {
                            lg.addLog(String.format("No.%-4d %s: Failed to check in!\nThe flight has already departed!\n", p.getIdx(), p.getFlightCode()));
                        }
                    }
                    // Clear the waiting queues
                    for (PassengerList pl : waitingQueuePanel.getWaitingQueues()) {
                        pl.clear();
                    }
                }
//                System.out.println(flightDetailsList);
                if (idx1.incrementAndGet() == flights.size()) {
                    idx1.set(0);
                }
            }
        });
        timer.start();
    }

    public static LocalTime getVirtualTime() {
        return virtualTime;
    }

    public static EventBoardPanel getInstance() {
        return instance;
    }



    public Timer getTimer() {
        return timer;
    }

}
