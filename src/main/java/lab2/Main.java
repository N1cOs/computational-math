package lab2;

import lab4.FunctionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        List<Function> functions = getFunctions();
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Доступные функции:");
            int index = 0;
            for(Function function : functions)
                System.out.println(++index + ": " + function.toString());
            System.out.println("Введите номер функции, интеграл которой хотели бы вычислить:");
            int number = Integer.parseInt(scanner.nextLine().trim());
            if(number > functions.size() || number < 1)
                throw new ArithmeticException("Выберите номер функции из списка");

            System.out.println("Введите пределы интегрирования через пробел:");
            String[] limits = scanner.nextLine().
                    trim().replace(',', '.').split("\\s+");
            if(limits.length > 2)
                throw new ArithmeticException("Введите только пределы интегрирования (два числа)");

            double lowerLimit = Double.parseDouble(limits[0]);
            double upperLimit = Double.parseDouble(limits[1]);
            if(lowerLimit == upperLimit)
                throw new ArithmeticException("Длина выбраного интервала должна быть больше нуля");

            System.out.println("Введите точность (например, 0.001):");
            double accuracy = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            if(accuracy <= 0 || accuracy >= 1)
                throw new ArithmeticException("Точность должна быть больше 0 и меньше 1");

            IntegralCalculator calculator = new IntegralCalculator(functions.get(--number), lowerLimit, upperLimit);
            double answer = calculator.integrate(accuracy);
            System.out.println("Значение интеграла: " + format(accuracy, answer));
            System.out.println("Количество разбиений: " + calculator.getNumberOfIntervals());
            System.out.println("Полученная погрешность: " + calculator.getRunge());
        }

        catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("Введите данные согласно инструкциям!!!");
        }
    }

    private static String format(double accuracy, double result){
        double temp = accuracy;
        int n = 1;
        while((temp *= 10) < 1){
            n++;
        }
        return String.format("%." + n + "f", result);
    }

    private static List<Function> getFunctions(){
        List<Function> functions = new ArrayList<>();
        Function function1 = new FunctionAdapter() {
            @Override
            public double getValue(double arg) {
                return 7 / (arg * arg + 1);
            }
            @Override
            public String toString() {
                return "7 / (x^2 + 1)";
            }
        };Function function2 = new FunctionAdapter() {
            @Override
            public double getValue(double arg) {
                return 2 * arg * arg + arg + 4;
            }
            @Override
            public String toString() {
                return "2x^2 + x + 4";
            }
        };Function function3 = new FunctionAdapter() {
            @Override
            public double getValue(double arg) {
                return arg / Math.sqrt(Math.pow(arg, 4) + 16);
            }
            @Override
            public String toString() {
                return "x / (x^4 + 16)^1/2";
            }
        };Function function4 = new FunctionAdapter() {
            @Override
            public double getValue(double arg) {
                return Math.pow(2, arg);
            }
            @Override
            public String toString() {
                return "2^x";
            }
        };
        functions.add(function1);
        functions.add(function2);
        functions.add(function3);
        functions.add(function4);
        return functions;
    }
}
