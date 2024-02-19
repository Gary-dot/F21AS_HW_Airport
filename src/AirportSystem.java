import DataStructure.*;
import GUI.ProgramGUI;
import Algorithm.*;

import static Algorithm.ReadLists.readFlightList;

public class AirportSystem {
    private PassengerList passengerList;
    private FlightList flightList;
    private FlightDetailsList flightDetailsList;

    public AirportSystem() {
        passengerList = new PassengerList();
        flightList = new FlightList();
        readFlightList(flightList);
        flightDetailsList = ReadLists.readPassengerList(passengerList, flightList);
    }
    public PassengerList getPassengerList() {
        return passengerList;
    }
    public FlightList getFlightList() {
        return flightList;
    }
    public void showGUI() {
        new ProgramGUI(passengerList, flightList, flightDetailsList).run();
    }
    public FlightDetailsList getFlightDetailsList() {
        return flightDetailsList;
    }

    /**
     * The main method.
     * @param args None.
     */
    public static void main(String[] args) {
        AirportSystem airportSystem = new AirportSystem();
        airportSystem.showGUI();
        new SystemControlInterface(airportSystem.getFlightDetailsList(), airportSystem.getFlightList()).run();
    }
}
