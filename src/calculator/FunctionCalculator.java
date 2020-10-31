package calculator;

public class FunctionCalculator {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double x, double y, double z) {
        return strategy.calculate(x, y, z);
    }
}
