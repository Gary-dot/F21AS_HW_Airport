package model.DataStructure.Exceptions;

public class FlightNotFoundException extends IllegalArgumentException{
    /**
     * Flight data error.
     */
    public FlightNotFoundException(String s) {
        super(s);
    }
    public FlightNotFoundException() {
        super("The passenger's flight number is not found.");
    }

    public static void main(String[] args) {
        try {
            throw new FlightNotFoundException("");
        } catch (FlightNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Program stopped");
        }
    }
}
