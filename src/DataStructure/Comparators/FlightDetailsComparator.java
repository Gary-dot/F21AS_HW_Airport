package DataStructure.Comparators;

import DataStructure.FlightDetails;
import java.util.Comparator;

public class FlightDetailsComparator implements Comparator<FlightDetails> {
    public int compare(FlightDetails f1, FlightDetails f2) {
        return f1.getFlightCode().compareTo(f2.getFlightCode());
    }
}
