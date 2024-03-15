package model.DataStructure;

import java.time.LocalTime;

public class UpcomingFlight {
    private Flight flight;
    private LocalTime checkInTime;
    public UpcomingFlight(Flight flight, LocalTime checkInTime) {
        this.flight = flight;
        this.checkInTime = checkInTime;
    }
    public Flight getFlight() {
        return flight;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

}
