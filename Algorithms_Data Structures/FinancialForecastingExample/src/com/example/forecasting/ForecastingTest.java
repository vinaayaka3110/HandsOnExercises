public class ForecastingTest {
    public static void main(String[] args) {
        double initialInvestment = 1000.0; 
        double annualGrowth = 0.07;       
        int projectionYears = 10;          

        System.out.println("--- Financial Forecasting Simulation Tool ---");
        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Assumed Growth Rate: " + (annualGrowth * 100) + "%\n");

        long startTime = System.nanoTime();
        double standardResult = FinancialForecasting.calculateFutureValueRecursive(initialInvestment, annualGrowth, projectionYears);
        long endTime = System.nanoTime();
        System.out.printf("[Standard Recursion Result]: Future value after %d years = $%.2f%n", projectionYears, standardResult);
        System.out.println("Execution time: " + (endTime - startTime) + " ns\n");

        startTime = System.nanoTime();
        double optimizedResult = FinancialForecasting.calculateFutureValueOptimized(initialInvestment, annualGrowth, projectionYears);
        long endTimeOptimized = System.nanoTime();
        System.out.printf("[Optimized Memoization Result]: Future value after %d years = $%.2f%n", projectionYears, optimizedResult);
        System.out.println("Execution time: " + (endTimeOptimized - startTime) + " ns");
    }
}