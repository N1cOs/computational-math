package lab3;

import lab2.Function;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Function function = (arg) -> Math.log(arg);
        double[] xData = {2, 6, 10, 14, 18};

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
