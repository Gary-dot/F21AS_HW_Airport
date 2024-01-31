package DataStructure.Exceptions;

public class CheckInErrorException extends IllegalArgumentException{
    /**
     * Check-in data error.
     */
    public CheckInErrorException() {
        super("Check-in record error!");
    }

    public static void main(String[] args) {
        try {
            throw new CheckInErrorException();
        } catch (CheckInErrorException e) {
            System.out.println(e.getMessage());
            System.out.println("Program stopped");
        }
    }
}
