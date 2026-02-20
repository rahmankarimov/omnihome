package com.omnihome.patterns.adapter;

public class GlorbThermostat {

    private int currentTempF;

    void setHeatIndex(int fahrenheit)
    {
        this.currentTempF = fahrenheit;
        System.out.println("Setting heat index to " + fahrenheit + "F");
    };

    public int getHeatIndex() {
        return currentTempF;
    }
}
