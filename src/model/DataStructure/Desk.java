package model.DataStructure;


import interfaces.Observer;
import interfaces.Subject;
import model.Algorithm.LogGenerator;
import views.Panels.*;

public class Desk implements Runnable, Subject {
    private int speed;
    private boolean stop;
    private final int id;
    private boolean isRunning;
    private Passenger p;
    private final Object lock = new Object();

    public Desk(int id) {
        this.isRunning = true;
        this.stop = true;
        this.id = id;
        this.speed = 1;
    }

    public synchronized void resume() {
        stop = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public synchronized void setDelay(int delay) {
        this.speed = delay;
    }

    public synchronized void stop() {
        stop = true;
    }

    public synchronized void close() {
        isRunning = false;
    }

    @Override
    public void run() {
        WaitingQueuePanel waitingQueuePanel = WaitingQueuePanel.getInstance();
        PassengerList waitingQueue = waitingQueuePanel.getWaitingQueue();
        while (isRunning) {
            synchronized (lock) {
                if (stop) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            p = waitingQueue.removeFirst();
            notifyObservers();
//            deskDetailsPanel.updateText();
            // Generate a random number
            int time = (int) (Math.random() * 500 + 750);
            try {
                Thread.sleep(time/speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Add the passenger to the FlightDetails, if the passenger is not late for the flight.
            if (p != null) {
                String s;
                FlightDetails fd = FlightDetailsList.getInstance().getByCode(p.getFlightCode());
                if (fd != null) {
                    fd.addPassenger(p);
                    // Update display
                    s = String.format("No.%-4d %s: Successfully check in at %s.\n", p.getIdx(), p.getReferenceCode(), EventBoardPanel.getVirtualTime().toString());
                } else {
                    // Update display
                    s = String.format("No.%-4d %s: Failed to check in at %s!\nThe flight has already departed!\n", p.getIdx(), p.getReferenceCode(), EventBoardPanel.getVirtualTime().toString());
                }
                LogGenerator.getInstance().addLog(s);
            }
        }
    }
    @Override
    public String toString() {
        if (p == null) {
            return String.format("Desk %d: \nNo passenger is being served.\n", id);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Desk %d: \n%s %-12s %-12s (%s)\n", id, p.getFlightCode(), p.getFirstName(), p.getLastName(), p.getBaggage().printBaggage()));
        if (p.getPenalty()[2] == 0) {
            sb.append("No baggage fee is charged.\n");
        } else {
            sb.append(String.format("A baggage fee is charged: %d£\n", p.getPenalty()[2]));
        }
        return sb.toString();
    }
    private Observer obs;
    @Override
    public void registerObserver(Observer obs) {
        this.obs = obs;
    }

    @Override
    public void removeObserver(Observer obs) {
        this.obs = null;
    }

    @Override
    public void notifyObservers() {
        obs.update();
    }
}
