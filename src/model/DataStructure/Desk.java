package DataStructure;

import GUI.Frames.InfoDisplay;
import GUI.Panels.DeskDetailsPanel;
import GUI.Panels.EventBoardPanel;
import GUI.Panels.FlightDetailsPanel;
import GUI.Panels.WaitingQueuePanel;
import GUI.ProgramGUI;
import Main.AirportSystem;

public class Desk implements Runnable {
    private int speed = 1;
    private boolean stop = false;
    private final int id;
    private boolean isRunning;
    private Passenger p;

    public Desk(int id) {
        this.isRunning = true;
        this.id = id;
    }

    public synchronized void resume() {
        stop = false;
    }

    public synchronized void setSpeed(int speed) {
        this.speed = speed;
    }

    public synchronized void stop() {
        stop = true;
    }

    public synchronized void close() {
        isRunning = false;
    }

    @Override
    public void run() {
        WaitingQueuePanel waitingQueuePanel = ProgramGUI.getWaitingQueuePanel();
        PassengerList waitingQueue = WaitingQueuePanel.getWaitingQueue();
        DeskDetailsPanel deskDetailsPanel = ProgramGUI.getDeskDetailsPanel();
        FlightDetailsPanel flightDetailsPanel = ProgramGUI.getFlightDetailsPanel();
        InfoDisplay infoDisplay = ProgramGUI.getInfoDisplay();
        while (isRunning) {
            synchronized (waitingQueuePanel) {
                if (stop) {
                    try {
                        waitingQueuePanel.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (waitingQueue.size() > 0) {
                    p = waitingQueue.removeFirst();
                    waitingQueuePanel.updateText();
                } else {
                    p = null;
                }
            }
            deskDetailsPanel.updateText();
            // Generate a random number
            int time = (int) (Math.random() * 500 + 750);
            try {
                Thread.sleep(time / speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Add the passenger to the FlightDetails, if the passenger is not late for the flight.
            if (p != null) {
                String s;
                FlightDetails fd = ProgramGUI.getFlightDetailsList().getByCode(p.getFlightCode());
                if (fd != null) {
                    fd.addPassenger(p);
                    flightDetailsPanel.updateTextArea();
                    // Update display
                    s = String.format("No.%-4d %s: Successfully check in at %s.", p.getIdx(), p.getReferenceCode(), EventBoardPanel.getVirtualTime().toString());
                } else {
                    // Update display
                    s = String.format("No.%-4d %s: Failed to check in at %s!\nThe flight has already departed!", p.getIdx(), p.getReferenceCode(), EventBoardPanel.getVirtualTime().toString());
                }
                infoDisplay.appendText(s);
                AirportSystem.log(s);
            }
        }
        deskDetailsPanel.updateText();
    }

    @Override
    public String toString() {
        if (p == null) {
            return String.format("Desk %d: \nNo passenger is being served.\n\n", id);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Desk %d: \n%s %-12s %-12s (%s %s)\n", id, p.getReferenceCode(), p.getFirstName(), p.getLastName(), p.getFlightCode(), p.getBaggage().printBaggage()));
        if (p.getPenalty()[2] == 0) {
            sb.append("No baggage fee is charged.\n\n");
        } else {
            sb.append(String.format("A baggage fee is charged: %d£\n\n", p.getPenalty()[2]));
        }
        return sb.toString();
    }
}
