package Algorithm;

import DataStructure.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static java.lang.System.exit;

public class SystemControlInterface {
    private FlightDetailsList flightDetailsList;
    private FlightList flightList;

    public SystemControlInterface(FlightDetailsList flightDetailsList, FlightList flightList) {
        this.flightDetailsList = flightDetailsList;
        this.flightList = flightList;
    }

    public static void main(String[] args) {
        new SystemControlInterface(new FlightDetailsList(), new FlightList()).run();
    }

    public void run() {
        System.out.println("Airplane System Management\n");
        System.out.println("Type quit or q to exit and generate a report.\n");
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            if (command.equals("q") || command.equals("quit")) {
                updateFlightDetailsList();
                generateReport();
                exit(0);
            }
        } while (!(command.equals("quit") || command.equals("q")));
    }

    /**
     * Update the flight details list by checking if the total weight and total volume of each flight
     */
    public void updateFlightDetailsList() {
        if (flightDetailsList != null) {
            for (FlightDetails fd : flightDetailsList.getFlightDetailsList()) {
                int totalWeight = fd.getTotalWeight();
                int totalVolume = fd.getTotalVolume();
                Flight flight = flightList.findByFlightCode(fd.getFlightCode());
                Baggage baggageLimit = flight.getBaggageLimit();
                int weightLimit = flight.getTotalWeightLimit();
                int volumeLimit = flight.getTotalVolumeLimit();
                if (totalWeight > weightLimit) {
                    fd.setWeightExcess(true);
                }
                if (totalVolume > volumeLimit) {
                    fd.setVolumeExcess(true);
                }
            }
        }
    }

    /**
     * Generate a report both to the console and to the file report.txt.
     * The report should contain the following information:
     * For each flight,
     * the number of passengers checked-in, the total weight of their baggage, the total volume of
     * their baggage, and the total excess baggage fees collected.
     */
    public void generateReport() {
        File file = new File(".\\output\\report.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                file.delete();
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            StringBuffer sb = new StringBuffer();
            sb.append("Flight-Report:\n\n" +
                    String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n\n", "Flight", "Passenger", "Total Weight", "Total Volume", "Total Fee", "Weight Excess?", "Volume Excess?"));
            sb.append(flightDetailsList.toString());
            fileWriter.write(sb.toString());
            System.out.println(sb);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Report Generated!\n");
        }
    }

}
