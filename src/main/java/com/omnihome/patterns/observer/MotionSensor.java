package com.omnihome.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class MotionSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void detectMotion() {
        System.out.println("Motion detected");
        notifyObservers();
    }
}
