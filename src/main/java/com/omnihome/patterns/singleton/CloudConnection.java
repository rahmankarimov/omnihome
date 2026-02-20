package com.omnihome.patterns.singleton;

public class CloudConnection {
    private String apiKey;
    private String serverUrl;

    private CloudConnection() {
        // Private constructor to prevent instantiation
    }

    private static class Holder {
        private static final CloudConnection INSTANCE = new CloudConnection();
    }

    public static CloudConnection getInstance() {
        return Holder.INSTANCE;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
