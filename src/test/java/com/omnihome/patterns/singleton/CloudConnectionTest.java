package com.omnihome.patterns.singleton;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class CloudConnectionTest {

    @Test
    void singletonReturnsSameInstance() {
        CloudConnection c1 = CloudConnection.getInstance();
        CloudConnection c2 = CloudConnection.getInstance();
        assertSame(c1, c2, "Expected the same singleton instance");
    }

    @Test
    void canStoreAndRetrieveApiKeyAndServerUrl() {
        CloudConnection conn = CloudConnection.getInstance();
        conn.setApiKey("ABC123");
        conn.setServerUrl("https://api.example.com");

        assertEquals("ABC123", conn.getApiKey());
        assertEquals("https://api.example.com", conn.getServerUrl());
    }

    @Test
    void singletonIsThreadSafe() throws InterruptedException, ExecutionException {
        // Try to get instance concurrently and ensure all are identical
        int threads = 20;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            List<Callable<CloudConnection>> callables = new ArrayList<>();
            for (int i = 0; i < threads; i++) {
                callables.add(CloudConnection::getInstance);
            }
            List<Future<CloudConnection>> futures = pool.invokeAll(callables);
            CloudConnection first = futures.getFirst().get();
            for (Future<CloudConnection> f : futures) {
                assertSame(first, f.get(), "All futures should return the same instance");
            }
        } finally {
            pool.shutdownNow();
        }
    }
}
