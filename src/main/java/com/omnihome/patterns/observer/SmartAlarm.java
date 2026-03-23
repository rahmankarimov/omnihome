package com.omnihome.patterns.observer;

import com.omnihome.patterns.strategy.AlertStrategy;
import java.util.HashMap;
import java.util.Map;

public class SmartAlarm implements Observer {
    private final Map<String, AlertStrategy> strategyRegistry = new HashMap<>();
    private AlertStrategy currentStrategy;

    public void registerStrategy(String key, AlertStrategy strategy) {
        strategyRegistry.put(key, strategy);
    }

    public void setStrategy(String key) {
        this.currentStrategy = strategyRegistry.get(key);
    }

    @Override
    public void update() {
        System.out.println("SmartAlarm: Motion detected!");
        if (currentStrategy != null) {
            currentStrategy.executeAlert();
        } else {
            System.out.println("Triggering default alarm...");
        }
    }
}
