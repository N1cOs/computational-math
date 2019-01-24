package lab4;

import lab2.Function;

public class Main {
    public static void main(String[] args) {
        Function function = new FunctionAdapter() {
            @Override
            public double getValue(double x, double y) {
                return (2 * (x * x + y));
            }
        };
        Function realSolution = new FunctionAdapter() {
            @Override
            public double getValue(double x) {
                return 1.5 * Math.pow(Math.E, 2 * x) - x * x - x - 0.5;
            }
        };

        DifferentialEquation equation = new DifferentialEquation(function);
        double[][] solution = equation.solve(0, 1, 1, 0.000001);
        for(int i = 0; i < solution[1].length; i++){
            System.out.println(Math.abs(realSolution.getValue(solution[0][i]) - solution[1][i]));
        }
        System.out.println(solution[1].length);
    }
}
