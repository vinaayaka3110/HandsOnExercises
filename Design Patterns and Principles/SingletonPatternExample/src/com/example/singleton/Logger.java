package com.example.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton implementation using the Initialization-on-demand holder idiom.
 * This approach guarantees lazy initialization and is completely thread-safe.
 */
public class Logger {

    // 1. Private constructor ensures no other class can type 'new Logger()'
    private Logger() {
        // Prevents bypassing constructor restriction via Java Reflection API
        if (LoggerHolder.INSTANCE != null) {
            throw new IllegalStateException("Logger instance already created.");
        }
    }

    // 2. Private static inner helper class that holds the single instance of
    // Logger.
    // It is loaded into memory only when Logger.getInstance() is called for the
    // first time.
    private static class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }

    // 3. Public static method that serves as the global access point to the single
    // instance
    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    /**
     * Helper method to output logs decorated with standard timestamps.
     * 
     * @param message Message to output
     */
    public void log(String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = dtf.format(LocalDateTime.now());
        System.out.printf("[%s] [INFO] %s%n", timestamp, message);
    }
}