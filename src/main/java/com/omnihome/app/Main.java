package com.omnihome.app;

import com.omnihome.patterns.adapter.GlorbAdapter;
import com.omnihome.patterns.adapter.GlorbThermostat;
import com.omnihome.patterns.builder.AutomationRoutine;
import com.omnihome.patterns.builder.RoutineBuilder;
import com.omnihome.patterns.factory.*;
import com.omnihome.patterns.prototype.DeviceConfiguration;
import com.omnihome.patterns.singleton.CloudConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the home simulation");

        System.out.println("Connecting to cloud");
        CloudConnection cloud = CloudConnection.getInstance();
        cloud.setServerUrl("https://api.omnihome.com");
        cloud.setApiKey("LUXURY_HOME_KEY");
        System.out.println("Connected to: " + cloud.getServerUrl());

        System.out.println("Setting up luxury devices");
        DeviceFactory factory = new LuxuryFactory();
        SmartLight livingRoomLight = factory.createLight();
        SmartThermostat mainThermostat = factory.createThermostat();
        SmartLock frontDoorLock = factory.createLock();
        
        livingRoomLight.turnOn();
        mainThermostat.setTemperature(22.0);
        frontDoorLock.lock();
        System.out.println("Living room is ready");

        System.out.println("Fixing the old thermostat");
        GlorbThermostat legacyGlorb = new GlorbThermostat();
        SmartThermostat adaptedThermostat = new GlorbAdapter(legacyGlorb);
        adaptedThermostat.setTemperature(20.5);
        System.out.println("Old thermostat is working at 20.5");

        System.out.println("Making movie night routine");
        AutomationRoutine movieNight = new RoutineBuilder()
                .withName("Movie Night")
                .addDevice(livingRoomLight)
                .addDevice(mainThermostat)
                .atTime("08:00 PM")
                .toggleNotification(true)
                .build();
        System.out.println("Routine " + movieNight.getName() + " ready for " + movieNight.getTriggerTime());

        System.out.println("Copying device settings");
        DeviceConfiguration masterConfig = new DeviceConfiguration("192.168.1.100", 8080, "v2.0.1");
        DeviceConfiguration secondaryConfig = masterConfig.clone();
        secondaryConfig.setIpAddress("192.168.1.101");
        
        System.out.println("Master IP: " + masterConfig.getIpAddress());
        System.out.println("New IP: " + secondaryConfig.getIpAddress());
        System.out.println("Firmware: " + secondaryConfig.getFirmwareVersion());

        System.out.println("Simulation finished");
    }
}
