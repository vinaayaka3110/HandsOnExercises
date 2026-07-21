package com.example.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("--- Testing Singleton Pattern Implementation --- \n");

        // Step 1: Request the Logger instance twice into separate variables
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Step 2: Log messages using both variable references
        logger1.log("Application pipeline sequence initiated.");
        logger2.log("Validating system memory boundaries...");

        System.out.println(); // Prints empty line for visual spacing

        // Step 3: Print out the unique memory hashcodes of both references
        System.out.println("Logger 1 System HashCode: " + logger1.hashCode());
        System.out.println("Logger 2 System HashCode: " + logger2.hashCode());

        // Step 4: Verify absolute identity using standard identity evaluation (==)
        if (logger1 == logger2) {
            System.out.println("\nSUCCESS: Both references point to the exact same instance!");
        } else {
            System.out.println("\nFAILURE: Multiple object instances were detected.");
        }

        // Optional Verification:
        // If you un-comment the code line below, it will trigger a compilation error
        // because the constructor has private access visibility.
        // Logger brokenLogger = new Logger();
    }
}