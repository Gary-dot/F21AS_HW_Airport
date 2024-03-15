package views.Panels;


import main.AirportSystem;
import model.Algorithm.LogGenerator;
import model.DataStructure.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static views.ProgramGUI.titleFont;

public class EventBoardPanel extends JPanel {
    private static LocalTime virtualTime = LocalTime.of(6, 0, 0); // Start at midnight
    private static final EventBoardPanel instance = new EventBoardPanel();
    private final JButton speedButton;
    private final JButton pauseButton;
    private final JButton exitButton;
    private final JTextArea noticeBoardTextArea;
    private final JLabel timeLabel;
    private final Timer timer;
    public static EventBoardPanel getInstance() {
        return instance;
    }
    private EventBoardPanel() {
        //    private FlightDetailsList flightDetailsList;
        setBorder(BorderFactory.createTitledBorder(null, "Event Board", TitledBorder.CENTER, TitledBorder.BELOW_TOP, titleFont));
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        speedButton = new JButton("  x1  ");
        pauseButton = new JButton("Pause");
        exitButton = new JButton("Quit");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JLabel t1 = new JLabel("(hh:mm)");
        t1.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font size of the title
        timeLabel = new JLabel(virtualTime.toString());
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Set the font size of the clock
        add(timeLabel, gbc);
        add(t1, gbc);

        gbc.weightx = 1;
        JPanel buttonPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 3, 5, 5);
        buttonPanel.setLayout(gridLayout);
        buttonPanel.add(speedButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(exitButton);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(buttonPanel, gbc);

        gbc.gridx = 0; // Reset to first column
        gbc.gridy = 1; // Move to second row
        gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span across the remainder of the row
        gbc.fill = GridBagConstraints.BOTH; // Fill horizontally only
        noticeBoardTextArea = new JTextArea();
        noticeBoardTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(noticeBoardTextArea);
        noticeBoardTextArea.setEditable(false);
        add(scrollPane, gbc);

        // Get some variables from ProgramGUI
        ArrayList<UpcomingFlight> upcomingFlights = new ArrayList<>();
        FlightDetailsList flightDetailsList = FlightDetailsList.getInstance();

        ArrayList<Flight> flights = FlightList.getInstance().getArrayList();
        AtomicInteger idx1 = new AtomicInteger(0);
        AtomicInteger idx2 = new AtomicInteger(0);

        // Always check in 2 hours before the flight
//        Flight firstFlight = flights.get(idx2.getAndIncrement());
//        upcomingFlights.add(new UpcomingFlight(firstFlight, firstFlight.getDepartureTime().minusHours(2)));
        for(Flight f : flights) {
            upcomingFlights.add(new UpcomingFlight(f, f.getDepartureTime().minusHours(2)));
        }



        noticeBoardTextArea.append("A new day begins!\n");
        // Create a timer to update the time every second
        timer = new Timer(1000, e -> {
            if (virtualTime.equals(LocalTime.of(11, 59, 0))) {
                noticeBoardTextArea.setText("");
                virtualTime = LocalTime.of(6, 0, 0);
                noticeBoardTextArea.append("A new day begins!\n");
            } else {
                virtualTime = virtualTime.plusMinutes(1);
            }
            timeLabel.setText(virtualTime.toString());
            if (virtualTime.equals(upcomingFlights.get(idx2.get()).getCheckInTime())) {
                String flightCode = upcomingFlights.getFirst().getFlight().getFlightCode();
                String destination = upcomingFlights.getFirst().getFlight().getDestination();
                String s = String.format("Flight %s(%s) is ready for check-in at %s.\n", flightCode, destination, virtualTime.toString());
                noticeBoardTextArea.append(s);
                LogGenerator.getInstance().addLog(s);
                FlightDetails fd = new FlightDetails(upcomingFlights.get(idx2.get()).getFlight());
                flightDetailsList.addDetails(fd);
                if (idx2.incrementAndGet() == flights.size()) {
                    idx2.set(0);
                }
            }
            if (virtualTime.equals(flights.get(idx1.get()).getDepartureTime())) {
                String flightCode = flights.get(idx1.get()).getFlightCode();
                String destination = flights.get(idx1.get()).getDestination();
                String s = String.format("Flight %s(%s) is ready to depart at %s.\n", flightCode, destination, virtualTime.toString());
                noticeBoardTextArea.append(s);
                LogGenerator.getInstance().addLog(s);
                flightDetailsList.removeDetails(flightCode);
                if (flightDetailsList.size() == 0) {
                    noticeBoardTextArea.append("All flights have departed.\n");
                }
//                System.out.println(flightDetailsList);
                if (idx1.incrementAndGet() == flights.size()) {
                    idx1.set(0);
                }
            }
        });
        timer.start();
    }
    public Timer getTimer() {
        return timer;
    }

    public JButton getExitButton() {
        return exitButton;
    }
    public JButton getSpeedButton() {
        return speedButton;
    }
    public JButton getPauseButton() {
        return pauseButton;
    }
}
