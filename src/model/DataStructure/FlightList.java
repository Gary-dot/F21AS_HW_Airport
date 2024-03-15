package model.DataStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static model.Algorithm.ListReader.readFlightList;

public class FlightList {
    private static FlightList instance;
    private HashMap<String, Flight> hashMap;
    private ArrayList<Flight> arrayList;

    /**
     * Perform any initialization for the address book.
     */
    private FlightList() {
        hashMap = new HashMap<String, Flight>();
        arrayList = new ArrayList<Flight>();
    }

    /**
     * Perform any initialization for the address book.
     * Only used for testing purposes.
     * @param path
     */
    public FlightList(String path) {
        hashMap = new HashMap<String, Flight>();
        arrayList = new ArrayList<Flight>();
        readFlightList(this, path);
    }

    public ArrayList<Flight> getArrayList() {
        return arrayList;
    }

    /**
     * Singleton pattern
     * @return
     */
    public static synchronized FlightList getInstance() {
        if (instance == null) {
            instance = new FlightList();
            readFlightList(instance, ".\\input\\FlightList.txt");
        }
        return instance;
    }

    /**
     * @return All the flight details in the order of the flight code.
     */
    public void listByDepartureTime() {
        arrayList.sort(Comparator.comparing(Flight::getDepartureTime));
    }

    /**
     * Add a new flight details.
     *
     * @param f The flight details to be added.
     */
    public void addDetails(Flight f) {
        hashMap.put(f.getFlightCode(), f);
        arrayList.add(f);
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
