package lab3;

import lab2.Function;
import lombok.AllArgsConstructor;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
public class Graphing {
    private Function baseFunction;
    private double[] xData;


    public JPanel getChart(int width, int height) {
        NewtonPolynomial polynomial = new NewtonPolynomial(baseFunction);
        Function function = polynomial.interpolate(xData);

        XYChart chart = new XYChart(width, height);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[xData.length - 1]);

        double[] yData = new double[xData.length];
        for (int i = 0; i < yData.length; i++)
            yData[i] = baseFunction.getValue(xData[i]);
        XYSeries points = chart.addSeries("Points", xData, yData);
        points.setMarker(SeriesMarkers.CIRCLE);
        points.setMarkerColor(Color.RED);
        points.setLineColor(Color.WHITE);

        double step = Math.abs(xData[xData.length - 1] - xData[0]) / width;
        double[] xGraphing = new double[width];
        double[] yBaseFunction = new double[width];
        double[] yPolynomial = new double[width];
        for (int i = 0; i < yBaseFunction.length; i++) {
            double arg = xData[0] + step * i;
            yBaseFunction[i] = baseFunction.getValue(arg);
            yPolynomial[i] = function.getValue(arg);
            xGraphing[i] = arg;
        }

        chart.addSeries("Исходная функция", xGraphing, yBaseFunction).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Интерполированная функции", xGraphing, yPolynomial).
                setMarker(SeriesMarkers.NONE);

        return new XChartPanel<>(chart);
    }
}