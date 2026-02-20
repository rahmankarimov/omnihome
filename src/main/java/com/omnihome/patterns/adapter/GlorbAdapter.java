package com.omnihome.patterns.adapter;

import com.omnihome.patterns.factory.SmartThermostat;

public class GlorbAdapter implements SmartThermostat {

    private final GlorbThermostat thermostat;

    public GlorbAdapter(GlorbThermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void setTemperature(double celsius) {
        int fahrenheit = (int) (celsius * 1.8 + 32);

        System.out.println("Adapter: Converting " + celsius + "C -> " + fahrenheit + "F");
        thermostat.setHeatIndex(fahrenheit);
    }

    public String getStatus() {
        return "Heating (Legacy Adapter) at " + thermostat.getHeatIndex() + "F";
    }
}
