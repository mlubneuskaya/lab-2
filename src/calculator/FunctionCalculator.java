package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionCalculator {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double x, double y, double z) {
        double result = strategy.calculate(x, y, z);
        if (!Double.isInfinite(result)) {
            result = BigDecimal.valueOf(result)
                    .setScale(6, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return result;
    }
}
