package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        List<Function> functions = getFunctions();

    }

    private static List<Function> getFunctions(){
        List<Function> functions = new ArrayList<>();
        Function function1 = new Function() {
            @Override
            public double getValue(double arg) {
                return 7 / (arg * arg + 1);
            }
            @Override
            public String toString() {
                return "7 / (x^2 + 1)";
            }
        };
        Function function2 = new Function() {
            @Override
            public double getValue(double arg) {
                return 2 * arg * arg + arg + 4;
            }
            @Override
            public String toString() {
                return "2x^2 + x + 4";
            }
        };Function function3 = new Function() {
            @Override
            public double getValue(double arg) {
                return arg / Math.sqrt(Math.pow(arg, 4) + 16);
            }
            @Override
            public String toString() {
                return "x / (x^4 + 16)^1/2";
            }
        };Function function4 = new Function() {
            @Override
            public double getValue(double arg) {
                return Math.pow(Math.E, 2 * arg);
            }
            @Override
            public String toString() {
                return "e^(2x)";
            }
        };Function function5 = new Function() {
            @Override
            public double getValue(double arg) {
                return 3 / arg + 1;
            }
            @Override
            public String toString() {
                return "3 / (x + 1)";
            }
        };
        functions.add(function1);
        functions.add(function2);
        functions.add(function3);
        functions.add(function4);
        functions.add(function5);
        return functions;
    }
}
