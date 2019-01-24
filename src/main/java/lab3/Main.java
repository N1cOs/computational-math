package lab3;

import lab2.Function;
import lab4.FunctionAdapter;

public class Main {

    public final static double CHANGE = 2;

    public static void main(String[] args) {
        Function function = new FunctionAdapter() {
            @Override
            public double getValue(double arg) {
                return Math.pow(Math.E, arg);
            }
        };
        UserInterface userInterface = new UserInterface(function);
        userInterface.draw(700, 900);
    }
}
