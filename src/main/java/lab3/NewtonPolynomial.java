package lab3;

import lab2.Function;

public class NewtonPolynomial {
    
    public Function interpolate(double[] xData, double[] yData) {
        double[] coeffs = new double[xData.length - 1];
        for (int i = 0; i < coeffs.length; i++)
            coeffs[i] = divideSubtractions(xData, yData, 0, i + 1);
        Function function = (arg) -> {
            double result = 0;
            for (int i = 0; i < coeffs.length; i++) {
                double temp = coeffs[i] * (arg - xData[0]);
                for (int j = 1; j <= i; j++) {
                    temp *= (arg - xData[j]);
                }
                result += temp;
            }
            result += yData[0];
            return result;
        };
        return function;
    }

    private double divideSubtractions(double[] xData, double[] yData, int startPosition, int endPosition) {
        if (endPosition - startPosition == 1) {
            return (yData[endPosition] - yData[startPosition]) /
                    (xData[endPosition] - xData[startPosition]);
        }
        return (divideSubtractions(xData, yData, startPosition + 1, endPosition) -
                divideSubtractions(xData, yData, startPosition, endPosition - 1)) / (xData[endPosition] - xData[startPosition]);
    }
}
