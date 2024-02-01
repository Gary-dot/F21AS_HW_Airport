package Algorithm;

public class ReferenceCodeRule {
    /**
     * The reference code is a unique identifier for the flight.
     * The reference looks like this: ORD7557
     * Specifically, the reference code starts with "ORD" followed by 4 digits.
     *
     * @param code
     * @return
     */
    public static boolean checkReferenceCodeValidity(String code) {
        if (code.length() != 7) {
            return false;
        }
        if (!code.startsWith("ORD")) {
            return false;
        }
        try {
            Integer.parseInt(code.substring(3));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
