package model.DataStructure;

import interfaces.Observer;
import interfaces.Subject;
import model.Algorithm.PassengerGenerator;
import views.Frames.ProgramGUI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The passenger list contains all the passengers.
 */
public class PassengerList implements Subject {
    private final ArrayList<Passenger> passengerList;
    private Observer obs;

    public PassengerList() {
        this.passengerList = new ArrayList<>();
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

    public void add(Passenger p) {
        synchronized (this) {
            passengerList.add(p);
        }
        notifyObservers();
    }

    public int size() {
        return passengerList.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        synchronized (this) {
            for (Passenger p : passengerList) {
                sb.append(p.toString());
            }
        }
        return sb.toString();
    }

    public Passenger get(int i) {
        return passengerList.get(i);
    }

    public Passenger getLastPassenger() {
        return passengerList.getLast();
    }

    public void clear() {
        synchronized (this) {
            passengerList.clear();
        }
        notifyObservers();
    }

    public Passenger removeFirst() {
        Passenger p;
        synchronized (this) {
            if (passengerList.isEmpty()) {
                return null;
            }
             p = passengerList.removeFirst();
        }
        notifyObservers();
        return p;
    }

    public void addARandomPassenger() {
        Passenger p = PassengerGenerator.generateARandomPassenger(FlightDetailsList.getInstance());
        if (p == null) {
            JOptionPane.showMessageDialog(null, "There is no FLIGHT!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        p.setIdx(ProgramGUI.getIdx3().getAndIncrement());
        add(p);
    }

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
        if (obs != null) {
            obs.update();
        }
    }
}
