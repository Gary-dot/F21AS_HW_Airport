package model.DataStructure;

import interfaces.Observer;
import interfaces.Subject;

public class FlightDetails implements Subject {
    private final String flightCode;
    private Flight flight;
    private int passengerNumber;
    private double totalWeight;
    private double totalVolume;
    private int totalExcessFee;
    public FlightDetails(String flightCode) {
        this.flightCode = flightCode;
        this.passengerNumber = 0;
        this.totalWeight = 0;
        this.totalVolume = 0;
        this.totalExcessFee = 0;
    }

    public FlightDetails(Flight f) {
        this.flightCode = f.getFlightCode();
        this.flight = f;
        this.passengerNumber = 0;
        this.totalWeight = 0;
        this.totalVolume = 0;
        this.totalExcessFee = 0;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getTotalExcessFee() {
        return totalExcessFee;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public synchronized void addPassenger(Passenger p) {
        this.passengerNumber++;
        this.totalWeight += p.getBaggage().getWeight();
        this.totalVolume += p.getBaggage().getVolume();
        this.totalExcessFee += p.getPenalty()[2];
        notifyObservers();
    }
    @Override
    public String toString() {
        return String.format("  %-22s%-12s%-4.2f%%   %-4.2f%%", this.flightCode + "(" + this.flight.getDestination() + ")", this.passengerNumber + "/" + flight.getMaxNumberOfPassengers(), this.totalWeight / flight.getTotalWeightLimit() * 100.0, this.totalVolume / flight.getTotalVolumeLimit() * 100.0);
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
