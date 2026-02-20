package com.omnihome.patterns.factory;

public class BudgetFactory implements DeviceFactory {
    @Override
    public SmartLight createLight() {
        return new BudgetLight();
    }

    @Override
    public SmartLock createLock() {
        return new BudgetLock();
    }

    @Override
    public SmartThermostat createThermostat() {
        return new BudgetThermostat();
    }

    public static class BudgetLight implements SmartLight {

        @Override
        public void turnOn() {
            System.out.println("Budget Light: on Plastic");
        }

        @Override
        public String getStatus() {
            return "ON";
        }
    }

    public static class BudgetLock implements SmartLock {

        @Override
        public void lock() {
            System.out.println("BudgetLock (No camera)");
        }

        @Override
        public String getStatus() {
            return "LOCKED (Basic)";
        }
    }
    private static class BudgetThermostat implements SmartThermostat {
        public void setTemperature(double temp) { System.out.println("Budget Thermostat: Slowly heating to " + temp); }
        public String getStatus() { return "Heating"; }
    }
}
