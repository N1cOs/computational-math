package lab3;

import lab2.Function;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        Function function = (arg) -> Math.log(arg);
        NewtonPolynomial polynomial = new NewtonPolynomial(function);
        double[] xData = {9, 13, 17, 21};
        Function function1 = polynomial.getPolynomial(xData);

        Graphing graphing = new Graphing(function, function1, xData);
        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame("Graphics");
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.add(graphing.getChart(500, 600));
            jFrame.pack();
            jFrame.setVisible(true);
        });
    }
}
