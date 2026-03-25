package com.omnihome.patterns.observer;

import com.omnihome.patterns.strategy.AlertStrategy;
import java.util.HashMap;
import java.util.Map;

public class SmartAlarm implements Observer {
    private final Map<String, AlertStrategy> strategyRegistry = new HashMap<>();
    private AlertStrategy currentStrategy;
    private boolean armed = false;

    public void arm() {
        this.armed = true;
        System.out.println("SmartAlarm: System ARMED. SmartAlarm is now ARMED.");
    }

    public void disarm() {
        this.armed = false;
        System.out.println("SmartAlarm: System DISARMED. SmartAlarm is now DISARMED.");
    }

    public void registerStrategy(String key, AlertStrategy strategy) {
        strategyRegistry.put(key, strategy);
    }

    public void setStrategy(String key) {
        this.currentStrategy = strategyRegistry.get(key);
    }

    @Override
    public void update() {
        if (armed) {
            System.out.println("SmartAlarm: Motion detected while ARMED!");
            if (currentStrategy != null) {
                currentStrategy.executeAlert();
            } else {
                System.out.println("Triggering default alarm...");
            }
        } else {
            System.out.println("SmartAlarm: Motion detected, but system is DISARMED. Ignoring...");
        }
    }
}
