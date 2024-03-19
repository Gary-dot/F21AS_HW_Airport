package model.DataStructure;

import interfaces.Observer;
import interfaces.Subject;

import java.util.ArrayList;

public class DeskList implements Subject {
    private static final DeskList instance = new DeskList();
    private final ArrayList<Desk> deskList;
    public static DeskList getInstance() {
        return instance;
    }
    private int speed;

    private DeskList() {
        deskList = new ArrayList<>();
        speed = 1;
    }

    public void addDesk() {
        synchronized(this){
            Desk d = new Desk(deskList.size() + 1);
            deskList.add(d);
            new Thread(d).start();
            d.setDelay(speed);
        }
        notifyObservers();
    }

    public void removeDesk() {
        synchronized(this){
            if (deskList.isEmpty()) {
                return;
            }
            Desk d = deskList.removeLast();
            d.close();
        }
        notifyObservers();
    }

    public synchronized void setSpeed(int s){
        speed = s;
        for (Desk d : deskList) {
            d.setDelay(speed);
        }
    }

    public synchronized void stopAll(){
        for (Desk d : deskList) {
            d.stop();
        }
    }

    public synchronized void resumeAll(){
        for (Desk d : deskList) {
            d.resume();
        }
    }

    public synchronized int size() {
        return deskList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Desk d : deskList) {
            sb.append(d.toString());
        }
        return sb.toString();
    }

    private Observer observer;
    @Override
    public void registerObserver(Observer obs) {
        observer = obs;
    }

    @Override
    public void removeObserver(Observer obs) {
        observer = null;
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            observer.update();
        }
    }
    public ArrayList<Desk> getArrayList() {
        return deskList;
    }
}
