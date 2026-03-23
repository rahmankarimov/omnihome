package com.omnihome.patterns.observer;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObserverTest {

    @Test
    public void testMotionSensorNotification() {
        MotionSensor sensor = new MotionSensor();
        SmartLights lights = new SmartLights();
        SmartAlarm alarm = new SmartAlarm();

        sensor.addObserver(lights);
        sensor.addObserver(alarm);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        sensor.detectMotion();

        String output = outContent.toString();
        assertTrue(output.contains("Motion detected!"));
        assertTrue(output.contains("SmartLights: Motion detected! Turning lights on..."));
        assertTrue(output.contains("SmartAlarm: Motion detected!"));
        assertTrue(output.contains("Triggering default alarm..."));

        System.setOut(System.out);
    }
}
