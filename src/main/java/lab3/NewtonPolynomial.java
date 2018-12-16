package lab3;

import lab2.Function;

public class NewtonPolynomial {

    private Function baseFunction;

    public NewtonPolynomial(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public Function getPolynomial(double[] args){
        return interpolate(args);
    }

    private Function interpolate(double[] args){
        final double h = args[1] - args[0];
        double[] deltaY = new double[args.length - 1];

        double[] values = new double[args.length];
        for(int i = 0; i < values.length; i++)
            values[i] = baseFunction.getValue(args[i]);

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
        
        
        Function function = new Function() {
            @Override
            public double getValue(double arg) {
                double result = 0;
                for(int i = 0; i < deltaY.length; i++){
                    double t = (arg - args[0]) / h;
                    for(int j = 1; j <= i; j++)
                        t *= (t - j);
                    result += t * deltaY[i] / Function.factorial(i + 1);
                }
                result += values[0];
                return result;
            }
        };

        return function;
    }
}
