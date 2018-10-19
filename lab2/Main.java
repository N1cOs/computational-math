package lab2;

public class Main {
    public static void main(String[] args){
        Function function = (arg) -> 7 / (arg * arg + 1);
        IntegralCalculator calculator = new IntegralCalculator(function, 5, 0);
        System.out.println(calculator.solve(0.0001));
    }
}
