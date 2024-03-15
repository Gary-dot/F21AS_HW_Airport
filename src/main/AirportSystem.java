package main;

import views.*;
import java.util.Locale;
public class AirportSystem {
    public AirportSystem() {
        Locale.setDefault(Locale.ENGLISH);
        new ProgramGUI();
    }

    /**
     * The main method.
     * @param args None.
     */
    public static void main(String[] args) {
        new AirportSystem();
    }
}
