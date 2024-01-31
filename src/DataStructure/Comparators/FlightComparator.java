package DataStructure.Comparators;

import DataStructure.Flight;

import java.util.Comparator;

/**
 * Defines an ordering on Flight objects on the flight code.
 */
public class FlightComparator implements Comparator<Flight> {
    public int compare(Flight f1, Flight f2) {
        return f1.getFlightCode().compareTo(f2.getFlightCode());
    }
}
