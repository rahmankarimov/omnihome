package com.omnihome.patterns.factory;

public class LuxuryFactory implements DeviceFactory{
    @Override
    public SmartLight createLight() {
        return new LuxuryLight();
    }

    @Override
    public SmartLock createLock() {
        return new LuxuryLock();
    }

    @Override
    public SmartThermostat createThermostat() {
        return new LuxuryThermostat();
    }

    public static class LuxuryLight implements SmartLight {
        @Override
        public void turnOn() {
            System.out.println("LuxuryLight (Glass)");
        }

        @Override
        public String getStatus() {
            return "ON(Bright)";
        }
    }

    private static class LuxuryLock implements SmartLock {
        public void lock() {
            System.out.println("Luxury Lock(Face ID)");
        }
        public String getStatus() {
            return "Locked (Secure)";
    }

}

    private static class LuxuryThermostat implements SmartThermostat {
        public void setTemperature(double temp) {
            System.out.println("Luxury Thermostat(AI) optimizing to " + temp);
        }
        public String getStatus() {
            return "Optimizing";
        }
    }
}
