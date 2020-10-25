package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.abs;

public class FunctionCalculator {
    private int formulaId = 1;
    public double calculate(double x, double y, double z){
        double result;
        if (formulaId == 1){
            result = calculateFunction1(x, y, z);
        }
        else result = calculateFunction2(x, y, z);
        result = BigDecimal.valueOf(result)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();

        return result;
    }
    private double calculateFunction1(double x, double y, double z) {
        if (y <= 0 || x == 0 || x == -1){
            throw new ArithmeticException();
        }
        double logarithm = log(pow((1+x), 2));
        double cosine = cos(Math.PI*pow(z, 3));
        if (isEven(sin(y)) && (logarithm + cosine) < 0){
            throw new ArithmeticException();
        }
        double firstTerm = pow((logarithm + cosine), sin(y));
        double exponent = pow(Math.E, pow(x, 2));
        cosine = cos(pow(Math.E, z));
        if(isEven(1/x) && (exponent + cosine + pow(y, -0.5)) < 0){
            throw new ArithmeticException();
        }
        double secondTerm = pow((exponent + cosine + pow(y, -0.5)), 1/x);
        return firstTerm + secondTerm;
    }
    private double calculateFunction2(double x, double y, double z) {
        if (x <= 0 || y == -1){
            throw new ArithmeticException();
        }
        double logarithm = log(pow((1+y), 2));
        double cosine = cos(Math.PI*pow(x, 3));
        if ((logarithm + cosine) < 0){
            throw new ArithmeticException();
        }
        double firstMultiplier = pow(logarithm + cosine, 0.25);
        double exponent = pow(Math.E, pow(z, 2));
        cosine = cos(pow(Math.E, y));
        double secondMultiplier = (exponent + cosine + pow(x, -0.5));
        return firstMultiplier*secondMultiplier;
    }
    public void setFormulaId(int id){
        this.formulaId = id;
    }
    public boolean isEven(double n){
        return (n % 2 == 0);
    }
}
