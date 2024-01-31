package DataStructure.Exceptions;

public class IllegalPassengerException extends IllegalArgumentException{
    /**
     * Passenger data error.
     */
    public IllegalPassengerException(int line) {
        super(String.format("Line %d: Wrong passenger data!", line));
    }

    public static void main(String[] args) {
        try {
            throw new IllegalPassengerException(1);
        } catch (IllegalPassengerException e) {
            System.out.println(e.getMessage());
            System.out.println("Program stopped");
        }
    }
}
