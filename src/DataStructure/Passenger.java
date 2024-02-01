package DataStructure;

import java.util.Arrays;
import java.util.SortedSet;

public class Passenger {
    private String referenceCode;
    private String lastName;
    private String firstName;
    private String flightCode;
    private boolean checkedIn;
    private Baggage baggage;
    private int[] penalty; // 0: weight, 1: volume, 2: excess fee

    /**
     * Constructor for objects of class Passenger
     *
     * @param referenceCode The reference code.
     * @param lastName      The last name.
     * @param firstName     The first name.
     * @param flightCode    The flight code.
     * @param checkedIn     The checked in status.
     */
    public Passenger(String referenceCode, String lastName, String firstName, String flightCode, boolean checkedIn) {
        this.referenceCode = referenceCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.flightCode = flightCode;
        this.checkedIn = checkedIn;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }

    public int[] getPenalty() {
        return penalty;
    }

    public void setPenalty(int[] penalty) {
        this.penalty = penalty;
    }


    /**
     * Test for content equality between two objects.
     * Only the reference code is used for the test.
     *
     * @param obj The object to compare to this one.
     * @return true if the argument object has same reference code
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Passenger otherPassenger) {
            return referenceCode.equals(otherPassenger.getReferenceCode());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if(checkedIn){
            return String.format("%-15s%-15s%-15s%-10s%-10s%-15s%-15d", referenceCode, lastName, firstName, flightCode, "Yes", baggage.toString(), penalty[2]);
        }else{
            return String.format("%-15s%-15s%-15s%-10s%-10s", referenceCode, lastName, firstName, flightCode, "No");
        }
    }
}