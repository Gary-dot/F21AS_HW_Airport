package model.DataStructure;

import interfaces.Observer;
import interfaces.Subject;
import model.DataStructure.Exceptions.FlightNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Observer pattern: FlightDetailsList is the subject, and the observer is the FlightDetailsPanel
 */
public class FlightDetailsList implements Subject {
    private static FlightDetailsList instance;
    private final HashMap<String, FlightDetails> flightDetailsMap;
    private final ArrayList<FlightDetails> flightDetailsList;
    private List<Observer> registeredObservers = new LinkedList<Observer>();

    private FlightDetailsList() {
        this.flightDetailsMap = new HashMap<>();
        this.flightDetailsList = new ArrayList<>();
    }

    // Singleton pattern
    public static synchronized FlightDetailsList getInstance() {
        if (instance == null) {
            instance = new FlightDetailsList();
        }
        return instance;
    }

    @Override
    public void registerObserver(Observer obs) {
        registeredObservers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        registeredObservers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : registeredObservers)
            obs.update();
    }

    public ArrayList<FlightDetails> getFlightDetailsList() {
        return flightDetailsList;
    }

    public void addDetails(FlightDetails f) {
        synchronized (this){
            // If the flight code is not already in the map, add it to the list
            if (flightDetailsMap.put(f.getFlightCode(), f) == null) {
                flightDetailsList.add(f);
            }
        }
        notifyObservers();
    }

    public void removeDetails(String flightCode) {
        synchronized (this) {
            flightDetailsMap.remove(flightCode);
            for (FlightDetails fd : flightDetailsList) {
                if (fd.getFlightCode().equals(flightCode)) {
                    flightDetailsList.remove(fd);
                    break;
                }
            }
        }
        notifyObservers();
    }

    public synchronized FlightDetails getByCode(String flightCode) {
        return flightDetailsMap.get(flightCode);
    }

//    public void updateDetails(Passenger p) throws FlightNotFoundException {
//        synchronized (this){
//            if (flightDetailsMap.containsKey(p.getFlightCode())) {
//                FlightDetails fd = flightDetailsMap.get(p.getFlightCode());
//                fd.addPassenger(p);
//            } else {
//                throw new FlightNotFoundException(String.format("Reference code %s: Flight %s not found.", p.getReferenceCode(), p.getFlightCode()));
//            }
//        }
//        notifyObservers();
//    }

    public int size() {
        return flightDetailsList.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (FlightDetails f : flightDetailsList) {
            sb.append(f.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
