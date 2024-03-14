package model.Algorithm;



import model.DataStructure.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

public class ListReader {
    public static void readFlightList(FlightList flightList) {
        BufferedReader buff = null;
        String[] fData;
        int lineCount = 0;
        try {
            buff = new BufferedReader(new FileReader(".\\input\\FlightList.txt"));
            String inputLine;  //read first line
            while (true) {
                //read next line
                inputLine = buff.readLine();
                if(inputLine == null) {
                    break;
                }
                lineCount++;
                //split line into parts
                fData = inputLine.split(";");
                if (fData.length != 9) {
                    System.out.printf("FlightList.txt: Line %d: Invalid flight data: %s%n", lineCount, inputLine);
                    continue;
                }
                int maxNumberOfPassengers;
                try {
                    maxNumberOfPassengers = Integer.parseInt(fData[3].trim());
                } catch (NumberFormatException nfe) {
                    System.out.printf("FlightList.txt: Line %d: Max number of passengers not a number: %s%n", lineCount, fData[3]);
                    continue;
                }

                int[] volumeLimit = new int[3];
                try {
                    String[] volume = fData[4].trim().split("x");
                    if (volume.length != 3) {
                        throw new IllegalArgumentException("Wrong number of volume limit.");
                    }
                    for (int i = 0; i < 3; i++) {
                        volumeLimit[i] = Integer.parseInt(volume[i].trim());
                    }
                } catch (NumberFormatException nfe) {
                    System.out.printf("FlightList.txt: Line %d: Length, width or height not a number :%s%n", lineCount, fData[3]);
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                int weightLimit;
                try {
                    weightLimit = Integer.parseInt(fData[5].trim());
                } catch (NumberFormatException nfe) {
                    System.out.printf("FlightList.txt: Line %d: Weight limit not a number: %s%n", lineCount, fData[5]);
                    continue;
                }

                int maxBaggageWeight;
                try {
                    maxBaggageWeight = Integer.parseInt(fData[6].trim());
                } catch (NumberFormatException nfe) {
                    System.out.printf("FlightList.txt: Line %d: Max baggage weight not a number: %s%n", lineCount, fData[6]);
                    continue;
                }
                int maxBaggageVolume;
                try {
                    maxBaggageVolume = Integer.parseInt(fData[7].trim());
                } catch (NumberFormatException nfe) {
                    System.out.printf("FlightList.txt: Line %d: Max baggage volume not a number: %s%n", lineCount, fData[7]);
                    continue;
                }
                LocalTime departureTime;
                try {
                    departureTime = LocalTime.parse(fData[8]);
                } catch (Exception e) {
                    System.out.printf("FlightList.txt: Line %d: Invalid departure time: %s%n", lineCount, fData[8]);
                    continue;
                }
                //create Flight object
                Baggage baggageLimit = new Baggage(volumeLimit[0], volumeLimit[1], volumeLimit[2], weightLimit);
                Flight f = new Flight(fData[0], fData[1], fData[2], maxNumberOfPassengers, baggageLimit, maxBaggageWeight, maxBaggageVolume,departureTime);
                flightList.addDetails(f);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                assert buff != null;
                buff.close();
                flightList.listByDepartureTime();
            } catch (IOException ioe) {
                //don't do anything
            }
        }
    }
}
