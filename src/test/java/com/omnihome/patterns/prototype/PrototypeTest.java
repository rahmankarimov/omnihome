package com.omnihome.patterns.prototype;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {

    @Test
    void cloningDeviceConfigurationCreatesIndependentCopy() {
        DeviceConfiguration original = new DeviceConfiguration("192.168.1.10", 8080, "v1.0.0");
        DeviceConfiguration duplicate = original.clone();

        assertNotSame(original, duplicate, "Cloned configuration should be a different object");
        assertEquals(original.getIpAddress(), duplicate.getIpAddress());
        assertEquals(original.getPort(), duplicate.getPort());
        assertEquals(original.getFirmwareVersion(), duplicate.getFirmwareVersion());

        // Modify the duplicate
        duplicate.setIpAddress("192.168.1.11");

        // Verify original is unchanged
        assertEquals("192.168.1.10", original.getIpAddress(), "Original IP should remain unchanged");
        assertEquals("192.168.1.11", duplicate.getIpAddress(), "Duplicate IP should be updated");
    }

    @Test
    void cloningMaintainsOtherProperties() {
        DeviceConfiguration original = new DeviceConfiguration("10.0.0.5", 9000, "v2.1.3");
        DeviceConfiguration duplicate = original.clone();

        duplicate.setPort(9001);
        duplicate.setFirmwareVersion("v2.1.4");

        assertEquals(9000, original.getPort());
        assertEquals("v2.1.3", original.getFirmwareVersion());
        assertEquals(9001, duplicate.getPort());
        assertEquals("v2.1.4", duplicate.getFirmwareVersion());
    }
}
