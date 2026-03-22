package com.omnihome.patterns.observer;

public class SmartLights implements Observer {
    @Override
    public void update() {
        System.out.println("SmartLights: Motion detected! Turning lights on...");
    }
}
