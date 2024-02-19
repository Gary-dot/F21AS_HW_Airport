package DataStructure.Comparators;

import DataStructure.Flight;

import java.util.Comparator;

/**
 * Defines an ordering on Flight objects on the flight code.
 */
public class FlightComparator implements Comparator<Flight> {
    public static final int FLIGHT_CODE = 0;
    public static final int AIRLINE = 1;
    public static final int DESTINATION = 2;
    private int type;
    public FlightComparator(int type) {
        this.type = type;
    }
    public int compare(Flight f1, Flight f2) {
        return switch (type) {
            case FLIGHT_CODE -> f1.getFlightCode().compareTo(f2.getFlightCode());
            case AIRLINE -> f1.getCarrier().compareTo(f2.getCarrier());
            case DESTINATION -> f1.getDestination().compareTo(f2.getDestination());
            default -> throw new IllegalArgumentException("Invalid compare type.");
        };
    }
}
