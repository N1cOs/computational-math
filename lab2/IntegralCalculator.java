package lab2;

public class IntegralCalculator {
    private double upperLimit;
    private double lowerLimit;
    private double intervalLength;
    private int numberOfIntervals;
    private Function function;

    public IntegralCalculator(Function function, double lowerLimit, double upperLimit){
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        intervalLength = Math.abs(upperLimit - lowerLimit);
        this.function = function;
    }


    private double trapezoidalRule(int intervals){
        double lengthOfPiece = intervalLength / intervals;
        double sum = 0;
        double currentPiece = Math.min(upperLimit, lowerLimit);
        for(int i = 1; i < intervals; i++){
            currentPiece += lengthOfPiece;
            sum += function.getValue(currentPiece);
        }
        double result = lengthOfPiece *
                ((function.getValue(lowerLimit) + function.getValue(upperLimit)) / 2 + sum);
        if(Double.isFinite(result)){
            return result;
        }
        else{
            throw new ArithmeticException();
        }
    }

    public double integrate(double accuracy){
        if(intervalLength == 0){
            return 0;
        }
        int intervals = 10;
        double result = trapezoidalRule(intervals);
        double result2 = trapezoidalRule(intervals*=2);
        while(Math.abs(result2 - result) / 3 > accuracy){
            result = result2;
            result2 = trapezoidalRule(intervals*=2);
        }
        numberOfIntervals = intervals;
        return result2;
    }

    public int getNumberOfIntervals() {
        return numberOfIntervals;
    }
}
