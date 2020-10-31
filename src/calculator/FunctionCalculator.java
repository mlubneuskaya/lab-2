package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionCalculator {
    private Function function;

    public void setFunction(Function function) {
        this.function = function;
    }

    public double calculate(double x, double y, double z) {
        double result = function.calculate(x, y, z);
        if (!Double.isInfinite(result)) {
            result = BigDecimal.valueOf(result)
                    .setScale(6, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return result;
    }
}
