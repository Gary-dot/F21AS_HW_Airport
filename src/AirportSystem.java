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
    public void showGUI() {
        new ProgramGUI(passengerList, flightList, flightDetailsList).setVisible(true);
    }

    /**
     * The main method.
     * @param args The arguments.
     */
    public static void main(String[] args) {
        new AirportSystem().showGUI();
        new SystemInterface().run();
    }
}
