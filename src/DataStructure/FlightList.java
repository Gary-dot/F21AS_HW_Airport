package DataStructure;

import DataStructure.Comparators.FlightComparator;
import DataStructure.Exceptions.FlightNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class FlightList {
    private HashMap<String, Flight> hashMap;
    private ArrayList<Flight> arrayList;

    /**
     * Perform any initialization for the address book.
     */
    public FlightList() {
        hashMap = new HashMap<String, Flight>();
        arrayList = new ArrayList<Flight>();
    }

    /**
     * Look up a flight code and return the
     * corresponding flight details.
     *
     * @param flightCode The flight code to be looked up.
     * @return The details corresponding to the flight code, null if none
     */
    public Flight findByFlightCode(String flightCode) throws FlightNotFoundException {
        if (hashMap.containsKey(flightCode)) {
            return hashMap.get(flightCode);
        }
        throw new FlightNotFoundException(String.format("Flight %s not found.", flightCode));
    }

    /**
     * @return All the flight details.
     */
    public String listDetals() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%-10s %-15s %-30s\n\n", "Flight", "Destination", "Airline"));
        for (Flight f : arrayList) {
            sb.append(String.format("%-10s %-15s %-30s\n", f.getFlightCode(), f.getDestination(), f.getCarrier()));
        }
        return sb.toString();
    }

    /**
     * @return All the flight details in the order of the flight code.
     */
    public String listByFlightCode() {
        arrayList.sort(new FlightComparator(FlightComparator.FLIGHT_CODE));
        return listDetals();
    }

    /**
     * @return All the flight details in the order of the airline.
     */
    public String listByAirline() {
        arrayList.sort(new FlightComparator(FlightComparator.AIRLINE));
        return listDetals();
    }

    /**
     * @return All the flight details in the order of the destination.
     */
    public String listByDestination() {
        arrayList.sort(new FlightComparator(FlightComparator.DESTINATION));
        return listDetals();
    }

    /**
     * Add a new flight details.
     * @param f The flight details to be added.
     */
    public void addDetails(Flight f) {
        hashMap.put(f.getFlightCode(), f);
        arrayList.add(f);
    }

    public FlightDetailsList initializeFlightDetailsList() {
        FlightDetailsList flightDetailsList = new FlightDetailsList();
        if(arrayList != null && !arrayList.isEmpty()) {
            for(Flight f : arrayList) {
                flightDetailsList.addDetails(new FlightDetails(f.getFlightCode()));
            }
        }
        return flightDetailsList;
    }

    public int size() {
        return arrayList.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Flight f : arrayList) {
            sb.append(f.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
