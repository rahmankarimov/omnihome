package com.omnihome.patterns.observer;

public class SmartAlarm implements Observer {
    @Override
    public void update() {
        System.out.println("SmartAlarm: Motion detected! Triggering alarm...");
    }
}
