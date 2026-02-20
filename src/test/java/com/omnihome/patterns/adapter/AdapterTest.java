package com.omnihome.patterns.adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdapterTest {

    @Test
    void adapterConvertsCelsiusToFahrenheit() {
        GlorbThermostat legacy = new GlorbThermostat();
        GlorbAdapter adapter = new GlorbAdapter(legacy);

        adapter.setTemperature(25.0);

        assertEquals(77, legacy.getHeatIndex(), "Expected 25C to be 77F");
        assertEquals("Heating (Legacy Adapter) at 77F", adapter.getStatus());
    }

    @Test
    void adapterHandlesNegativeTemperatures() {
        GlorbThermostat legacy = new GlorbThermostat();
        GlorbAdapter adapter = new GlorbAdapter(legacy);

        adapter.setTemperature(-10.0);

        assertEquals(14, legacy.getHeatIndex(), "Expected -10C to be 14F");
        assertTrue(adapter.getStatus().contains("14F"));
    }
}
