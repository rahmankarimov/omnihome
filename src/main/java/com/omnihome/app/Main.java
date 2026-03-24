package com.omnihome.app;

import com.omnihome.patterns.adapter.GlorbAdapter;
import com.omnihome.patterns.adapter.GlorbThermostat;
import com.omnihome.patterns.builder.AutomationRoutine;
import com.omnihome.patterns.builder.RoutineBuilder;
import com.omnihome.patterns.factory.*;
import com.omnihome.patterns.observer.MotionSensor;
import com.omnihome.patterns.observer.SmartAlarm;
import com.omnihome.patterns.observer.SmartLights;
import com.omnihome.patterns.prototype.DeviceConfiguration;
import com.omnihome.patterns.singleton.CloudConnection;
import com.omnihome.patterns.observer.*;
import com.omnihome.patterns.strategy.*;
import com.omnihome.patterns.command.*;

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

        System.out.println("Setting up security system with strategies");
        MotionSensor motionSensor = new MotionSensor();
        SmartLights lights = new SmartLights();
        SmartAlarm alarm = new SmartAlarm();

        alarm.registerStrategy("SILENT", new SilentPushStrategy());
        alarm.registerStrategy("SIREN", new LoudSirenStrategy());

        alarm.setStrategy("SILENT");

        motionSensor.addObserver(lights);
        motionSensor.addObserver(alarm);

        System.out.println("Testing security system - SILENT mode");
        motionSensor.detectMotion();

        System.out.println("Swapping to SIREN mode");
        alarm.setStrategy("SIREN");
        motionSensor.detectMotion();


        System.out.println("Testing the Remote & Command");
        SmartRemote remote = new SmartRemote();
        remote.setCommand(0, new TurnOnLightCommand(livingRoomLight));
        remote.setCommand(1, new ArmAlarmCommand(alarm));

        System.out.println("Pressing Button 0 (Lights On)");
        remote.pressButton(0);
        System.out.println("Pressing Button 1 (Arm Alarm)");
        remote.pressButton(1);

        System.out.println("Pressing Undo (Should disarm alarm)");
        remote.pressUndo();

        System.out.println("Simulation finished");
    }
}
