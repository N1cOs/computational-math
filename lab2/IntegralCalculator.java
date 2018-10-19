package lab2;

public class IntegralCalculator {
    private double upperLimit;
    private double lowerLimit;
    private double intervalLength;
    private Function function;

    public IntegralCalculator(Function function, double lowerLimit, double upperLimit){
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        intervalLength = Math.abs(upperLimit - lowerLimit);
        this.function = function;
    }

    public String solve(double accuracy){
        double temp = accuracy;
        int n = 1;
        while((temp *= 10) < 1){
            n++;
        }
        return String.format("%." + n + "f", integrate(accuracy));
    }


    private double trapezoidalRule(int intervals){
        double lengthOfPiece = intervalLength / intervals;
        double sum = 0;
        double currentPiece = Math.min(upperLimit, lowerLimit);
        for(int i = 1; i < intervals; i++){
            currentPiece += lengthOfPiece;
            sum += function.getValue(currentPiece);
        }
        return lengthOfPiece *
                ((function.getValue(lowerLimit) + function.getValue(upperLimit)) / 2 + sum);
    }

    private double integrate(double accuracy){
        int intervals = 10;
        double result = trapezoidalRule(intervals);
        double result2 = trapezoidalRule(intervals*=2);
        while(Math.abs(result2 - result) / 3 > accuracy){
            result = result2;
            result2 = trapezoidalRule(intervals*=2);
        }
        return result2;
    }
}
