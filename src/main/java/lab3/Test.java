package lab3;

import lab2.Function;

import javax.swing.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Function function = (arg) -> Math.log(arg);
        double[] xData = {2, 6, 10, 14, 18, 22, 26};
//        NewtonPolynomial polynomial = new NewtonPolynomial(function);
//        List<Function> functions = polynomial.getPolynomial(xData);
//        for(double i = 2; i < 5; i += 0.1){
//            System.out.printf("x:%f, %f\n", i, functions.get(0).getValue(i));
//        }

        Graphing graphing = new Graphing(function, xData);
        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame("Graphics");
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.add(graphing.getChart(500, 600));
            jFrame.pack();
            jFrame.setVisible(true);
        });
    }
}
