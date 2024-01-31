package DataStructure;

public class FlightDetails implements Comparable<FlightDetails>{
    private final String flightCode;
    private int passengerNumber;
    private int totalWeight;
    private int totalVolume;
    private int totalExcessFee;
    private boolean weightExcess;
    private boolean volumeExcess;

    public FlightDetails(String flightCode) {
        this.flightCode = flightCode;
        this.passengerNumber = 0;
        this.totalWeight = 0;
        this.totalVolume = 0;
        this.totalExcessFee = 0;
        this.weightExcess = false;
        this.volumeExcess = false;
    }
    public String getFlightCode() {
        return flightCode;
    }
    public boolean isWeightExcess() {
        return weightExcess;
    }

    public void setWeightExcess(boolean weightExcess) {
        this.weightExcess = weightExcess;
    }

    public boolean isVolumeExcess() {
        return volumeExcess;
    }

    public void setVolumeExcess(boolean volumeExcess) {
        this.volumeExcess = volumeExcess;
    }

    public void addPassenger(Passenger p) {
        this.passengerNumber++;
        this.totalWeight += p.getBaggage().getWeight();
        this.totalVolume += p.getBaggage().getVolume();
        this.totalExcessFee += p.getPenalty()[2];
    }

    @Override
    public int compareTo(FlightDetails o) {
        return this.flightCode.compareTo(o.flightCode);
    }

    @Override
    public String toString() {
        return String.format("%-10s%-10d%-10d%-10d%-10d%-10s%-10s", this.flightCode, this.passengerNumber, this.totalWeight, this.totalVolume, this.totalExcessFee, this.weightExcess, this.volumeExcess);
    }
}
