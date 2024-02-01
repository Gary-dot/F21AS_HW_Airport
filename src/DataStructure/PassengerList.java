package DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;

/**
 * The passenger list contains all the passengers.
 */
public class PassengerList {
    public final static int PASSENGER_NOT_FOUND = 2;
    public final static int PASSENGER_NOT_CHECKED_IN = 1;
    public final static int PASSENGER_CHECKED_IN = 0;
    private HashMap<String, Passenger> passengers;
    private ArrayList<Passenger> passengerList;

    public PassengerList() {
        this.passengers = new HashMap<>();
        this.passengerList = new ArrayList<>();
    }

    public void addDetails(Passenger p) {
        if (passengers.get(p.getReferenceCode()) == null) {
            passengers.put(p.getReferenceCode(), p);
            passengerList.add(p);
        }
    }

    public Passenger findByReferenceCode(String referenceCode) {
        return passengers.get(referenceCode);
    }

    public int checkIn(String referenceNumber, String lastName) {
        Passenger p = findByReferenceCode(referenceNumber);
        if (p != null) {
            if (p.getLastName().equals(lastName)) {
                if (p.isCheckedIn()) {
                    return 0;
                } else {
//                    p.setCheckedIn(true);
                    return 1;
                }
            }
        }
        return 2;
    }

    public Passenger getPassenger(String referenceNumber) {
        return passengers.get(referenceNumber);
    }

    public int size() {
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
}
