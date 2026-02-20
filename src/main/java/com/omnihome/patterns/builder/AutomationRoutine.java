package com.omnihome.patterns.builder;

import com.omnihome.patterns.factory.SmartDevice;

import java.util.ArrayList;
import java.util.List;

public class AutomationRoutine {

    private final String name;
    private final List<SmartDevice> devices;
    private final String triggerTime;
    private final boolean sendNotification;

    AutomationRoutine(String name, List<SmartDevice> devices, String triggerTime, boolean sendNotification) {
        this.name = name;
        this.devices = new ArrayList<>(devices);
        this.triggerTime = triggerTime;
        this.sendNotification = sendNotification;
    }

    public String getName() {
        return name;
    }

    public List<SmartDevice> getDevices() {
        return new ArrayList<>(devices);
    }

    public String getTriggerTime() {
        return triggerTime;
    }

    public boolean isSendNotification() {
        return sendNotification;
    }
}
