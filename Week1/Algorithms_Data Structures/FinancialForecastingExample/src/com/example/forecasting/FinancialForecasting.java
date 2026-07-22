import java.util.HashMap;
import java.util.Map;

public class FinancialForecasting {

    // --- Standard Recursive Approach ---
    public static double calculateFutureValueRecursive(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValueRecursive(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    // --- Optimized Recursive Approach (Memoization) ---
    private static Map<Integer, Double> memoCache = new HashMap<>();

    public static double calculateFutureValueOptimized(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        if (memoCache.containsKey(years)) {
            return memoCache.get(years);
        }
        double result = calculateFutureValueOptimized(presentValue, growthRate, years - 1) * (1 + growthRate);
        memoCache.put(years, result);
        return result;
    }
}
