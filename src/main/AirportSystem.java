package main;

import model.Algorithm.PassengerGenerator;
import views.*;
import java.util.Locale;
public class AirportSystem {
    public AirportSystem() {
        Locale.setDefault(Locale.ENGLISH);
        new ProgramGUI();
    }

    public void run() {
        Thread t = new Thread(PassengerGenerator.getInstance());
        t.start();
    }

    /**
     * The main method.
     * @param args None.
     */
    public static void main(String[] args) {
        new AirportSystem().run();
    }
}
