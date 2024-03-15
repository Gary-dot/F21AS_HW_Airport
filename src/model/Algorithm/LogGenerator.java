package model.Algorithm;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogGenerator {
    private static LogGenerator instance;
    private File logFile;
    private final StringBuilder logString;

    private LogGenerator() {
        logString = new StringBuilder();
        logFile = null;
    }

    public static synchronized LogGenerator getInstance() {
        if (instance == null) {
            instance = new LogGenerator();
        }
        return instance;
    }
    public synchronized void addLog(String message) {
        logString.append(message);
    }
    public void generateLogFile() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        String formatDateTime = now.format(formatter);
        logFile = new File(".\\logs\\%s.log".formatted(formatDateTime));
        try {
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                logFile.delete();
                logFile.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(logFile);
            fileWriter.write(logString.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Log Generated!\n");
        }
    }
}
