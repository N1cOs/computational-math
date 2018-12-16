package lab3;

import lab2.Function;

public class NewtonPolynomial {

    private Function baseFunction;

    public NewtonPolynomial(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public Function getPolynomial(double[] args){
        return interpolate(args, true);
    }

    private Function interpolate(double[] args, boolean isForward){
        final double h = args[1] - args[0];

        double[] values = new double[args.length];
        for(int i = 0; i < values.length; i++)
            values[i] = baseFunction.getValue(args[i]);
        double[] deltaY = findingDeltaY(values, true);

        
        
        Function function = new Function() {
            @Override
            public double getValue(double arg) {
                double result = 0;
                for(int i = 0; i < deltaY.length; i++){
                    double t = isForward ? (arg - args[0]) / h : (arg - args[args.length - 1]) / h;
                    for(int j = 1; j <= i; j++)
                        t *= (t - j);
                    result += t * deltaY[i] / Function.factorial(i + 1);
                }
                result += isForward ? values[0] : values[values.length - 1];
                return result;
            }
        };

        return function;
    }

    private double[] findingDeltaY(double[] values, boolean isForward){
        double[] deltaY = new double[values.length - 1];
        for(int i = 0; i < deltaY.length; i++){
            for(int j = 0; j <= i; j++){
                int factorial = Function.factorial(j + 1);
                double temp = Math.pow(-1, j + 1) * values[i - j] / factorial;
                for(int k = 0; k <= j; k++)
                    temp *= (i - k + 1);
                deltaY[i] += temp;
            }
            deltaY[i] += values[i + 1];
        }
        return deltaY;
    }
}
