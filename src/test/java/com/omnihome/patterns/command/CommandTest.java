package com.omnihome.patterns.command;

import com.omnihome.patterns.observer.SmartAlarm;
import com.omnihome.patterns.factory.LuxuryFactory;
import com.omnihome.patterns.factory.SmartLight;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {

    @Test
    public void testRemoteAndUndo() {
        SmartRemote remote = new SmartRemote();
        SmartLight light = new LuxuryFactory().createLight();
        SmartAlarm alarm = new SmartAlarm();

        remote.setCommand(0, new TurnOnLightCommand(light));
        remote.setCommand(1, new ArmAlarmCommand(alarm));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Test Light On
        remote.pressButton(0);
        assertTrue(outContent.toString().contains("LuxuryLight"));

        outContent.reset();

        // Test Arm Alarm
        remote.pressButton(1);
        assertTrue(outContent.toString().contains("SmartAlarm is now ARMED."));

        outContent.reset();

        // Test Undo (Disarm Alarm)
        remote.pressUndo();
        assertTrue(outContent.toString().contains("SmartAlarm is now DISARMED."));

        System.setOut(System.out);
    }
}
