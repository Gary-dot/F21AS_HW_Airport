package Algorithm;

import DataStructure.*;
import DataStructure.Exceptions.CheckInErrorException;
import DataStructure.Exceptions.IllegalFlightException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadLists {
    public static void readFlightList(FlightList flightList) {
        BufferedReader buff = null;
        String[] fData;
        int lineCount = 0;
        try {
            buff = new BufferedReader(new FileReader(".\\input\\FlightList.txt"));
            String inputLine = buff.readLine();  //read first line
            while (inputLine != null) {
                lineCount++;
                //split line into parts
                fData = inputLine.split(";");
                if (fData.length != 8) {
                    throw new IllegalFlightException(String.format("Line %d: Invalid flight data in line: %s", lineCount, inputLine));
                }
                int maxNumberOfPassengers = 0;
                try {
                    maxNumberOfPassengers = Integer.parseInt(fData[3].trim());
                } catch (NumberFormatException nfe) {
                    System.out.printf("%s: Max number of passengers not a number :%s%n", fData[0], fData[2]);
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
                    System.out.printf("%s: Length, width or height not a number :%s%n", fData[0], fData[3]);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                int weightLimit = 0;
                try {
                    weightLimit = Integer.parseInt(fData[5].trim());
                } catch (NumberFormatException nfe) {
                    System.out.println(fData[0] + ": Weight limit not a number :" + fData[5]);
                }

                int maxBaggageWeight = 0;
                try {
                    maxBaggageWeight = Integer.parseInt(fData[6].trim());
                } catch (NumberFormatException nfe) {
                    System.out.println(fData[0] + ": Max baggage weight not a number :" + fData[6]);
                }
                int maxBaggageVolume = 0;
                try {
                    maxBaggageVolume = Integer.parseInt(fData[7].trim());
                } catch (NumberFormatException nfe) {
                    System.out.println(fData[0] + ": Max baggage volume not a number :" + fData[7]);
                }
                //create Flight object
                Baggage baggageLimit = new Baggage(volumeLimit[0], volumeLimit[1], volumeLimit[2], weightLimit);
                Flight f = new Flight(fData[0], fData[1], fData[2], maxNumberOfPassengers, baggageLimit, maxBaggageWeight, maxBaggageVolume);
                flightList.addDetails(f);
                //read next line
                inputLine = buff.readLine();
            }
        } catch (IllegalFlightException e) {
            System.out.println(e.getMessage());
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
            } catch (IOException ioe) {
                //don't do anything
            }
        }
    }

    public static FlightDetailsList readPassengerList(PassengerList passengerList, FlightList flightList) {
        BufferedReader buff = null;
        FlightDetailsList flightDetailsList = flightList.initializeFlightDetailsList();
        String[] pData;
        int lineCount = 0;
        try {
            buff = new BufferedReader(new FileReader(".\\input\\PassengerList.txt"));
            String inputLine = buff.readLine();  //read first line
            while (inputLine != null) {
                lineCount++;
                //split line into parts
                pData = inputLine.split(";");
                if (pData.length == 0) {
                    System.out.printf("Line %d: Empty passenger!%n", lineCount);
                    continue;
                }
                if (pData.length != 5 && pData.length != 7) {
                    System.out.printf("Line %d: Invalid passenger data: %s%n", lineCount, inputLine);
                    continue;
                }
                if (!ReferenceCodeRule.checkReferenceCodeValidity(pData[0])) {
                    System.out.printf("Line %d: Invalid reference code: %s%n", lineCount, pData[0]);
                    continue;
                }
                int c = Integer.parseInt(pData[4].trim());
                if (c < 0 || c > 1) {
                    System.out.printf("Line %d: Invalid check-in status: %s%n", lineCount, pData[4]);
                }
                boolean checkedIn = Integer.parseInt(pData[4].trim()) != 0;
                if (!checkedIn && pData.length == 5) {
                    Passenger p = new Passenger(pData[0], pData[1], pData[2], pData[3], checkedIn);
                    //add to list
                    passengerList.addDetails(p);
                } else if (checkedIn && pData.length == 7) {
                    Passenger p = new Passenger(pData[0], pData[1], pData[2], pData[3], checkedIn);
                    // volume[] = {length, width, height}
                    int[] volume = new int[3];
                    int weight = 0;
                    try {
                        String[] v = pData[5].trim().split("x");
                        if (v.length != 3) {
                            throw new IllegalArgumentException("Wrong number of baggage volume.");
                        }
                        for (int i = 0; i < 3; i++) {
                            volume[i] = Integer.parseInt(v[i].trim());
                        }
                        weight = Integer.parseInt(pData[6].trim());
                    } catch (NumberFormatException nfe) {
                        System.out.printf("Line %d: Length, weight or height not a number :%s%n", lineCount, pData[5]);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        // Baggage may throw WrongBaggageSizeFormatException extends IllegalArgumentException
                        p.setBaggage(new Baggage(volume[0], volume[1], volume[2], weight));
                        // findByFlightCode may throw FlightNotFoundException extends IllegalArgumentException
                        Flight f = flightList.findByFlightCode(p.getFlightCode());
                        p.setPenalty(PenaltyRule.calculateExcessPenalty(f, p.getBaggage()));
                        // May throw FlightNotFoundException extends IllegalArgumentException
                        flightDetailsList.updateDetails(p);
                    } catch (IllegalArgumentException iae) { // Superclass of WrongBaggageSizeFormatException and FlightNotFoundException
                        System.out.printf("Line %d: %s%n", lineCount, iae.getMessage());
                    }
                    //add to list
                    passengerList.addDetails(p);
                }
                //read next line
                inputLine = buff.readLine();
            }
        } catch (CheckInErrorException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                assert buff != null;
                buff.close();
            } catch (IOException ioe) {
                //don't do anything
            }
        }
        return flightDetailsList;
    }
    public static FlightDetailsList readLists(PassengerList passengerList, FlightList flightList) {
        readFlightList(flightList);
        return readPassengerList(passengerList, flightList);
    }

}
