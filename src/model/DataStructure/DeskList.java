package DataStructure;

import GUI.Frames.InfoDisplay;
import GUI.Panels.DeskDetailsPanel;
import GUI.Panels.FlightDetailsPanel;
import GUI.Panels.WaitingQueuePanel;
import GUI.ProgramGUI;

import java.util.ArrayList;

public class DeskList {
    private final ArrayList<Desk> deskList;
    private boolean stop;
    private int speed;

    public DeskList() {
        deskList = new ArrayList<>();
        this.stop = false;
        this.speed = 1;
    }

    public synchronized void addDesk() {
        Desk d = new Desk(deskList.size() + 1);
        deskList.add(d);
        new Thread(d).start();
        d.setSpeed(speed);
        if(stop){
            d.stop();
        }
    }

    public synchronized void removeDesk() {
        Desk d = deskList.removeLast();
        d.close();
    }

    public synchronized void setSpeed(int speed){
        this.speed = speed;
        for (Desk d : deskList) {
            d.setSpeed(speed);
        }
    }

    public synchronized void stopAll(){
        this.stop = true;
        for (Desk d : deskList) {
            d.stop();
        }
    }

    public synchronized void resumeAll(){
        this.stop = false;
        for (Desk d : deskList) {
            d.resume();
        }
        WaitingQueuePanel waitingQueuePanel = ProgramGUI.getWaitingQueuePanel();
        synchronized (waitingQueuePanel) {
            waitingQueuePanel.notifyAll();
        }
    }

    public synchronized int size() {
        return deskList.size();
    }
    public synchronized void clearDesks() {
        while (!deskList.isEmpty()) {
            removeDesk();
        }
        ProgramGUI.getDeskDetailsPanel().updateText();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Desk d : deskList) {
            sb.append(d.toString());
        }
        return sb.toString();
    }


}
