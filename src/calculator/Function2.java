package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.pow;
import static java.lang.Math.log;
import static java.lang.Math.cos;

public class Function2 implements Strategy {

    @Override
    public double calculate(double x, double y, double z) {
        double logarithm = log(pow((1 + y), 2));
        double cosine = cos(Math.PI * pow(x, 3));
        Double firstMultiplier = pow(logarithm + cosine, 0.25);
        double exponent = pow(Math.E, pow(z, 2));
        cosine = cos(pow(Math.E, y));
        Double secondMultiplier = (exponent + cosine + pow(x, -0.5));
        if (firstMultiplier.isNaN() || secondMultiplier.isNaN()) {
            throw new ArithmeticException();
        }
        double result = firstMultiplier + secondMultiplier;
        result = BigDecimal.valueOf(result)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();

        return result;
    }
}
