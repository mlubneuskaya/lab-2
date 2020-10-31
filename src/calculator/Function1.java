package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class Function1 implements Strategy {
    @Override
    public double calculate(double x, double y, double z) {
        double logarithm = log(pow((1 + x), 2));
        double cosine = cos(Math.PI * pow(z, 3));
        Double firstTerm = pow((logarithm + cosine), sin(y));
        double exponent = pow(Math.E, pow(x, 2));
        cosine = cos(pow(Math.E, z));
        Double secondTerm = pow((exponent + cosine + pow(y, -0.5)), 1 / x);
        if (firstTerm.isNaN() || secondTerm.isNaN()) {
            throw new ArithmeticException();
        }
        double result = firstTerm + secondTerm;
        result = BigDecimal.valueOf(result)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();

        return result;
    }
}
