package lab2;

public class IntegralCalculator {
    private double upperLimit;
    private double lowerLimit;
    private double intervalLength;
    private int numberOfIntervals;
    private double runge;
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
        double result= lengthOfPiece *
                ((function.getValue(lowerLimit) + function.getValue(upperLimit)) / 2 + sum);
        if(Double.isFinite(result)){
            return result;
        }
        else{
            throw new ArithmeticException(
                    "Некоторые значения из указанного промежутка " +
                    "не удовлетворяют ОДЗ функции");
        }
    }

    public double integrate(double accuracy){
        int intervals = 10;
        double result1= trapezoidalRule(intervals);
        double result2 = trapezoidalRule(intervals*=2);
        while((runge = Math.abs(result2 - result1) / 3) > accuracy){
            if(intervals * 2 >= 10_000_000){
                throw new ArithmeticException("Достигнут максимум разбиений");
            }
            result1= result2;
            result2 = trapezoidalRule(intervals*=2);
        }
        numberOfIntervals = intervals;
        return result2;
    }

    public int getNumberOfIntervals() {
        return numberOfIntervals;
    }

    public double getRunge() {
        return runge;
    }
}
