package DataStructure.Exceptions;

public class IllegalFlightException extends IllegalArgumentException{
    /**
     * Flight data error.
     */
    public IllegalFlightException(String s) {
        super(s);
    }

    public static void main(String[] args) {
        try {
            throw new IllegalFlightException("Wrong flight data.");
        } catch (IllegalFlightException e) {
            System.out.println(e.getMessage());
            System.out.println("Program stopped");
        }
    }
}
