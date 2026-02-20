package com.omnihome.patterns.prototype;

public class DeviceConfiguration implements Cloneable {

    private String ipAddress;
    private int port;
    private String firmwareVersion;

    public DeviceConfiguration(String ipAddress, int port, String firmwareVersion) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.firmwareVersion = firmwareVersion;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    @Override
    public DeviceConfiguration clone() {
        try {
            return (DeviceConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }
}
