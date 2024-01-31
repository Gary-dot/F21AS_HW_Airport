package DataStructure;

public class Flight {
    private final String flightCode;
    private final String destination;
    private final String carrier;
    private final int maxNumberOfPassengers;
    private Baggage baggageLimit;
    private final int totalWeightLimit;
    private final int totalVolumeLimit;

    /**
     * The flightCode is a unique identifier for the flight.
     * The carrier is the airline that operates the flight.
     * The maxNumberOfPassengers represents the maximum number of passengers allowed on the flight.
     * The totalWeightLimit is measured in pounds.
     * The totalVolumeLimit is measured in cubic inches.
     *
     * @param flightCode            The flight code.
     * @param carrier               The carrier.
     * @param maxNumberOfPassengers The maximum number of passengers.
     * @param totalWeightLimit      The maximum baggage weight in pounds.
     * @param totalVolumeLimit      The maximum baggage volume in cubic inches.
     */
    public Flight(String flightCode, String destination, String carrier, int maxNumberOfPassengers, Baggage baggage, int totalWeightLimit, int totalVolumeLimit) {
        this.flightCode = flightCode;
        this.destination = destination;
        this.carrier = carrier;
        this.maxNumberOfPassengers = maxNumberOfPassengers;
        this.baggageLimit = baggage;
        this.totalWeightLimit = totalWeightLimit;
        this.totalVolumeLimit = totalVolumeLimit;
    }

    /**
     * @return The flight code.
     */
    public String getFlightCode() {
        return flightCode;
    }

    /**
     * @return The carrier.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * @return The maximum number of passengers.
     */
    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    /**
     * @return The maximum baggage weight.
     */
    public int getTotalWeightLimit() {
        return totalWeightLimit;
    }

    /**
     * @return The maximum baggage volume.
     */
    public int getTotalVolumeLimit() {
        return totalVolumeLimit;
    }

    public Baggage getBaggageLimit() {
        return baggageLimit;
    }

    public void setBaggageLimit(Baggage baggageLimit) {
        this.baggageLimit = baggageLimit;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-15s%-30s%-10d%-15s%-10d%-10d", flightCode, destination, carrier, maxNumberOfPassengers, baggageLimit.toString(), totalWeightLimit, totalVolumeLimit);
    }
}
