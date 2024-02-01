package DataStructure;

import DataStructure.Exceptions.FlightNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class FlightDetailsList {
    private HashMap<String, FlightDetails> flightDetailsMap;
    private ArrayList<FlightDetails> flightDetailsList;

    public FlightDetailsList() {
        this.flightDetailsMap = new HashMap<>();
        this.flightDetailsList = new ArrayList<>();
    }

    public ArrayList<FlightDetails> getFlightDetailsList() {
        return flightDetailsList;
    }

    public void addDetails(FlightDetails f) {
        // if the flight code is not in the map
        if (flightDetailsMap.put(f.getFlightCode(), f) == null) {
            flightDetailsList.add(f);
        }
    }

    public void updateDetails(Passenger p) throws FlightNotFoundException {
        if (flightDetailsMap.containsKey(p.getFlightCode())) {
            FlightDetails fd = flightDetailsMap.get(p.getFlightCode());
            fd.addPassenger(p);
        }else{
            throw new FlightNotFoundException(String.format("Reference code %s: Flight %s not found.", p.getReferenceCode(), p.getFlightCode()));
        }
    }

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
