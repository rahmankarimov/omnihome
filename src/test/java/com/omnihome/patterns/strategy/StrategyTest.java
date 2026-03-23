package com.omnihome.patterns.strategy;

import com.omnihome.patterns.observer.MotionSensor;
import com.omnihome.patterns.observer.SmartAlarm;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategyTest {

    @Test
    public void testAlarmStrategySwap() {
        MotionSensor sensor = new MotionSensor();
        SmartAlarm alarm = new SmartAlarm();
        
        alarm.registerStrategy("SILENT", new SilentPushStrategy());
        alarm.registerStrategy("SIREN", new LoudSirenStrategy());
        
        sensor.addObserver(alarm);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        alarm.setStrategy("SILENT");
        sensor.detectMotion();
        assertTrue(outContent.toString().contains("Sending silent push notification to homeowner's phone."));

        outContent.reset();

        alarm.setStrategy("SIREN");
        sensor.detectMotion();
        assertTrue(outContent.toString().contains("SOUNDING 120dB SIREN!"));

        System.setOut(System.out);
    }
}
