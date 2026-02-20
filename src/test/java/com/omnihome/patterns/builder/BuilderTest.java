package com.omnihome.patterns.builder;

import com.omnihome.patterns.factory.BudgetFactory;
import com.omnihome.patterns.factory.DeviceFactory;
import com.omnihome.patterns.factory.LuxuryFactory;
import com.omnihome.patterns.factory.SmartDevice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {

    @Test
    void canBuildVacationModeRoutine() {
        DeviceFactory budget = new BudgetFactory();
        DeviceFactory luxury = new LuxuryFactory();

        AutomationRoutine vacationMode = new RoutineBuilder()
                .withName("Vacation Mode")
                .addDevice(budget.createLight())
                .addDevice(budget.createLock())
                .addDevice(luxury.createLight())
                .addDevice(luxury.createThermostat())
                .atTime("08:00 AM")
                .toggleNotification(true)
                .build();

        assertEquals("Vacation Mode", vacationMode.getName());
        assertEquals(4, vacationMode.getDevices().size());
        assertEquals("08:00 AM", vacationMode.getTriggerTime());
        assertTrue(vacationMode.isSendNotification());
    }

    @Test
    void buildThrowsExceptionWhenNameIsMissing() {
        RoutineBuilder builder = new RoutineBuilder()
                .atTime("10:00 PM");

        assertThrows(IllegalStateException.class, builder::build, "Should throw exception if name is missing");
    }

    @Test
    void canBuildRoutineWithMinimumProperties() {
        AutomationRoutine routine = new RoutineBuilder()
                .withName("Simple Routine")
                .build();

        assertEquals("Simple Routine", routine.getName());
        assertTrue(routine.getDevices().isEmpty());
        assertNull(routine.getTriggerTime());
        assertFalse(routine.isSendNotification());
    }
}
