package DataStructure.Exceptions;

public class FlightNotFoundException extends IllegalArgumentException{
    /**
     * Flight data error.
     */
    public FlightNotFoundException(String s) {
        super(s);
    }

    public static void main(String[] args) {
        try {
            throw new FlightNotFoundException("Wrong flight data.");
        } catch (FlightNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Program stopped");
        }
    }
}
