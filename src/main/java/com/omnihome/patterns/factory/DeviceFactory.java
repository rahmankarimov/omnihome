package com.omnihome.patterns.factory;

public interface DeviceFactory {
    SmartLight createLight();
    SmartLock createLock();
    SmartThermostat createThermostat();
}
