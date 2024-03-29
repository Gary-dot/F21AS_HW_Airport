package model.DataStructure;

public class Passenger {
    private int idx;
    private String referenceCode;
    private String lastName;
    private String firstName;
    private String flightCode;
    private Baggage baggage;
    private int[] penalty; // 0: weight, 1: volume, 2: Total fee
    /**
     * Constructor for objects of class Passenger
     *
     * @param referenceCode The reference code.
     * @param lastName      The last name.
     * @param firstName     The first name.
     * @param flightCode    The flight code.
     */
    public Passenger(String referenceCode, String lastName, String firstName, String flightCode, boolean checkedIn, Baggage baggage, int[] penalty) {
        this.referenceCode = referenceCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.flightCode = flightCode;
        this.baggage = baggage;
        this.penalty = penalty;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
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

    /**
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("No.%-5d %-10s %-9s %-10s %s\n", idx, referenceCode, firstName, lastName, baggage.printBaggage());
    }
}