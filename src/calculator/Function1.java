package calculator;

import static java.lang.Math.*;

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
        return firstTerm + secondTerm;
    }
}
