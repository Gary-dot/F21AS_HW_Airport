package model.Algorithm;
import model.DataStructure.*;
import views.Panels.WaitingQueuePanel;

import java.util.Random;


/**
 * This class is responsible for generating random passengers.
 */
public class PassengerGenerator implements Runnable {
    private static final PassengerGenerator instance = new PassengerGenerator();

    /**
     * Get the instance of the passenger generator.
     * Singleton pattern.
     * @return The instance of the passenger generator.
     */
    public static PassengerGenerator getInstance() {
        return instance;
    }
    private static final String[] firstNames = {"John", "Jane", "Alice", "Michael", "Emily", "David", "Sophia", "Daniel", "Olivia",
            "Matthew", "Isabella", "James", "Mia", "Jacob", "Charlotte", "Ethan", "Amelia",
            "Alexander", "Harper", "Henry", "Evelyn", "Benjamin", "Abigail", "Christopher",
            "Emily", "Liam", "Madison", "Noah", "Elizabeth", "Lucas", "Avery", "Mason",
            "Sofia", "Logan", "Ella", "Oliver", "Scarlett", "Samuel", "Grace", "Nathan",
            "Chloe", "Caleb", "Isabelle", "Jack", "Natalie", "Luke", "Lily", "Gavin", "Leah"
    };
    private static final String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez",
            "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor",
            "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez",
            "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright",
            "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green", "Adams", "Nelson", "Baker",
            "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts", "Gomez", "Phillips", "Evans"
    };
    private int delay;
    private static Random random = new Random();
    private boolean stop = false;
    private boolean isRunning = true;
    public void setDelay(int delay) {
        this.delay = delay;
    }
    private PassengerGenerator(){
        this.delay = 1000;
    }

    public static Passenger generateAPassenger(Flight f) {
        // 1. Generate a random reference code based on the rule.
        String referenceCode = generateRandomReferenceCode();
        // 2. Generate a random last name.
        String lastName = generateRandomLastName();
        // 3. Generate a random first name.
        String firstName = generateRandomFirstName();
        // 4. Generate a flight code based on the current flight table.
        String flightCode = f.getFlightCode();
        // 5. Generate a random baggage.
        Baggage baggage = generateRandomBaggage();
        // 6. Calculate the penalty for the baggage.
        int[] penalty = PenaltyRule.calculateExcessPenalty(f, baggage);
        return new Passenger(referenceCode, lastName, firstName, flightCode, false, baggage, penalty);
    }

    /**
     * Generate a random passenger based on the current flight table.
     *
     * @param fdl The current flight table.
     * @return
     */
    public static Passenger generateARandomPassenger(FlightDetailsList fdl) {
        if (fdl.size() == 0) {
            return null;
        }
        FlightDetails fd = fdl.getFlightDetailsList().get(random.nextInt(fdl.size()));
        return generateAPassenger(fd.getFlight());
    }

    /**
     * Generate a list of random passengers based on the current flight table every minute.
     * More flights, more passengers will be generated.
     *
     * @param fdl The current flight table.
     * @return A list of random passengers.
     */
    public PassengerList generatePassengers(FlightDetailsList fdl) {
        PassengerList pl = new PassengerList();
        for (FlightDetails fd : fdl.getFlightDetailsList()) {
            double chance = random.nextDouble();
            int n;
            if (chance < 0.7) {
                n = 0;
            } else if (chance < 0.9) {
                n = 1;
            } else if (chance < 0.95) {
                n = 2;
            } else {
                n = 3;
            }
            for (int i = 0; i < n; i++) {
                pl.add(generateAPassenger(fd.getFlight()));
            }
        }
        return pl;
    }

    /**
     * Generate a list of random passengers when a flight starts to check in.
     *
     * @param fd The flight details.
     * @return A list of random passengers.
     */
    public static PassengerList generatePassengers(FlightDetails fd) {
        PassengerList pl = new PassengerList();
        // Mean and standard deviation for N(40, 10)
        double mean = 20;
        double stdDeviation = 5;
        int num = (int) (mean + (random.nextGaussian() * stdDeviation));
        if (num < 5) {
            num = 5;
        }
        for (int i = 0; i < num; i++) {
            pl.add(generateAPassenger(fd.getFlight()));
        }
        return pl;
    }

    public static Baggage generateRandomBaggage() {
        // 1. Generate a random weight, and it must follow normal distribution N(40, 10).
        Random random = new Random();
        // Mean and standard deviation for N(40, 10)
        double mean = 40;
        double stdDeviation = 10;
        // Generate a random value following N(40, 10)
        double randomWeight = mean + (random.nextGaussian() * stdDeviation);

        int l, w, h;
        l = w = h = 0;
        // 2. Generate a random length, width and height, and they must follow normal distribution N(μ, σ).
        while (!(l >= w && w >= h && h > 0)) {
            l = (int) (24 + (random.nextGaussian() * 3));
            w = (int) (18 + (random.nextGaussian() * 3));
            h = (int) (12 + (random.nextGaussian() * 3));
        }
        return new Baggage(l, w, h, (int) randomWeight);
    }

    public static String generateRandomReferenceCode() {
        StringBuilder referenceCode = new StringBuilder("ORD");
        for (int i = 0; i < 6; i++) {
            referenceCode.append((int) (Math.random() * 10));
        }
        return referenceCode.toString();
    }

    public static String generateRandomFirstName() {
        // Generate a random name from a very large list of names.
        return firstNames[(int) (Math.random() * firstNames.length)];
    }

    public static String generateRandomLastName() {
        // Generate a random name from a very large list of names.
        return lastNames[(int) (Math.random() * lastNames.length)];
    }

    public void stop() {
        this.stop = true;
    }

    public void close() {
        isRunning = false;
    }

    public synchronized void resume() {
        stop = false;
        this.notify();
    }

    @Override
    public void run() {
        WaitingQueuePanel waitingQueuePanel = WaitingQueuePanel.getInstance();
        FlightDetailsList flightDetailsList = FlightDetailsList.getInstance();
        while (isRunning) {
            PassengerList pl = generatePassengers(flightDetailsList);
            waitingQueuePanel.appendWaitingQueue(pl);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stop) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
