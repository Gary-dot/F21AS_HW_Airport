package DataStructure.Exceptions;

public class WrongBaggageSizeFormatException extends IllegalArgumentException{
    public WrongBaggageSizeFormatException(String s) {
        super(s);
    }

    public WrongBaggageSizeFormatException() {
        super("Wrong Baggage size format. \nPlease note that length >= width >= height.");
    }

    public static void main(String[] args) {
        try {
            throw new WrongBaggageSizeFormatException();
        } catch (WrongBaggageSizeFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Program stopped");
        }
    }
}
