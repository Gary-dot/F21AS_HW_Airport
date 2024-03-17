package model.DataStructure;

import interfaces.Observer;
import interfaces.Subject;
import model.Algorithm.LogGenerator;
import model.Algorithm.PassengerGenerator;
import views.Panels.EventBoardPanel;
import views.ProgramGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The passenger list contains all the passengers.
 */
public class PassengerList implements Subject {
    private HashMap<String, Passenger> passengers;
    private ArrayList<Passenger> passengerList;
    private Observer obs;

    public PassengerList() {
        this.passengers = new HashMap<>();
        this.passengerList = new ArrayList<>();
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

    public synchronized void add(Passenger p) {
        if (passengers.get(p.getReferenceCode()) == null) {
            passengers.put(p.getReferenceCode(), p);
            passengerList.add(p);
        }
        notifyObservers();
    }

    public synchronized int size() {
        return passengerList.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Passenger p : passengerList) {
            sb.append(p.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public Passenger get(int i) {
        return passengerList.get(i);
    }

    public Passenger getLastPassenger() {
        return passengerList.getLast();
    }

    public synchronized void clear() {
        passengers.clear();
        passengerList.clear();
        notifyObservers();
    }

    public synchronized Passenger removeFirst() {
        Passenger p = passengerList.removeFirst();
        passengers.remove(p.getReferenceCode());
        notifyObservers();
        return p;
    }

    public synchronized void addARandomPassenger() {
        Passenger p = PassengerGenerator.generateARandomPassenger(FlightDetailsList.getInstance());
        if (p == null) {
            JOptionPane.showMessageDialog(null, "There is no FLIGHT!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        p.setIdx(ProgramGUI.getIdx3().getAndIncrement());
        this.add(p);
        notifyObservers();
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
