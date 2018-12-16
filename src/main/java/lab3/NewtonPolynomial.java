package lab3;

import lab2.Function;

import java.util.LinkedList;
import java.util.List;

public class NewtonPolynomial {

    private Function baseFunction;

    public NewtonPolynomial(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public List<Function> getPolynomial(double[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("Args's length should be greater than 1");

        double[] forwardX;
        double[] backwardX;
        int separate = args.length / 2;
        if (args.length == 2) {
            forwardX = args;
            backwardX = null;
        } else {
            if (args.length % 2 == 0) {
                forwardX = new double[separate + 1];
                backwardX = new double[args.length - separate];
            } else {
                forwardX = new double[separate + 1];
                backwardX = new double[separate + 1];
            }
            System.arraycopy(args, 0, forwardX, 0, forwardX.length);
            System.arraycopy(args, separate, backwardX, 0, backwardX.length);
        }


        List<Function> functions = new LinkedList<>();
        double h = args[1] - args[0];
        functions.add(interpolateConstH(forwardX, h, true));
        if (backwardX != null)
            functions.add(interpolateConstH(backwardX, h, false));

        return functions;
    }

    private Function interpolateConstH(double[] args, double h, boolean isForward) {
        double[] values = new double[args.length];
        for (int i = 0; i < values.length; i++)
            values[i] = baseFunction.getValue(args[i]);

        double[] deltaY = new double[args.length - 1];
        if (isForward) {
            for (int i = 0; i < deltaY.length; i++)
                deltaY[i] = getDeltaY(values, 0, i + 1);
        } else {
            for (int i = 0; i < deltaY.length; i++)
                deltaY[i] = getDeltaY(values, deltaY.length - i - 1, i + 1);
        }

        Function function = new Function() {
            @Override
            public double getValue(double arg) {
                double result = 0;
                for (int i = 0; i < deltaY.length; i++) {
                    double t = isForward ? (arg - args[0]) / h : (arg - args[args.length - 1]) / h;
                    for (int j = 1; j <= i; j++)
                        t *= isForward ? (t - j) : (t + j);
                    result += t * deltaY[i] / Function.factorial(i + 1);
                }
                result += isForward ? values[0] : values[values.length - 1];
                return result;
            }
        };

        return function;
    }


    private double getDeltaY(double[] values, int index, int power) {
        double result = 0;
        for (int i = 0; i <= power; i++) {
            double temp = Math.pow(-1, i) * values[power + index - i] / Function.factorial(i);
            for (int j = 0; j < i; j++)
                temp *= (power - j);
            result += temp;
        }
        return result;
    }

    public Function interpolate(double[] args) {
        double[] coeffs = new double[args.length - 1];
        for (int i = 0; i < coeffs.length; i++)
            coeffs[i] = divideSubtractions(args, 0, i + 1);
        Function function = (arg) -> {
            double result = 0;
            for (int i = 0; i < coeffs.length; i++) {
                double temp = coeffs[i] * (arg - args[0]);
                for (int j = 1; j <= i; j++) {
                    temp *= (arg - args[j]);
                }
                result += temp;
            }
            result += baseFunction.getValue(args[0]);
            return result;
        };
        return function;
    }

    private double divideSubtractions(double[] args, int startPosition, int endPosition) {
        if (endPosition - startPosition == 1) {
            return (baseFunction.getValue(args[endPosition]) - baseFunction.getValue(args[startPosition])) /
                    (args[endPosition] - args[startPosition]);
        }
        return (divideSubtractions(args, startPosition + 1, endPosition) -
                divideSubtractions(args, startPosition, endPosition - 1)) / (args[endPosition] - args[startPosition]);
    }

}
