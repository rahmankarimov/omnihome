package com.omnihome.patterns.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @Test
    void budgetFactoryCreatesDevicesWithExpectedStatuses() {
        DeviceFactory factory = new BudgetFactory();

        SmartLight light = factory.createLight();
        SmartLock lock = factory.createLock();
        SmartThermostat thermostat = factory.createThermostat();

        assertNotNull(light);
        assertNotNull(lock);
        assertNotNull(thermostat);

        assertEquals("ON", light.getStatus());
        assertEquals("LOCKED (Basic)", lock.getStatus());
        assertEquals("Heating", thermostat.getStatus());

        light.turnOn();
        lock.lock();
        thermostat.setTemperature(22.5);
    }

    @Test
    void luxuryFactoryCreatesDevicesWithExpectedStatuses() {
        DeviceFactory factory = new LuxuryFactory();

        SmartLight light = factory.createLight();
        SmartLock lock = factory.createLock();
        SmartThermostat thermostat = factory.createThermostat();

        assertNotNull(light);
        assertNotNull(lock);
        assertNotNull(thermostat);

        assertEquals("ON(Bright)", light.getStatus());
        assertEquals("Locked (Secure)", lock.getStatus());
        assertEquals("Optimizing", thermostat.getStatus());

        // Sanity: interfaces behave as expected
        light.turnOn();
        lock.lock();
        thermostat.setTemperature(24.0);
    }

    @Test
    void factoriesReturnDifferentImplementations() {
        DeviceFactory budget = new BudgetFactory();
        DeviceFactory luxury = new LuxuryFactory();

        SmartLight budgetLight = budget.createLight();
        SmartLight luxuryLight = luxury.createLight();

        assertNotSame(budgetLight.getClass(), luxuryLight.getClass(), "Expected different light implementations");

        SmartLock budgetLock = budget.createLock();
        SmartLock luxuryLock = luxury.createLock();

        assertNotSame(budgetLock.getClass(), luxuryLock.getClass(), "Expected different lock implementations");

        SmartThermostat budgetThermo = budget.createThermostat();
        SmartThermostat luxuryThermo = luxury.createThermostat();

        assertNotSame(budgetThermo.getClass(), luxuryThermo.getClass(), "Expected different thermostat implementations");
    }
}
