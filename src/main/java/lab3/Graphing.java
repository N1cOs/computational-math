package lab3;

import lab2.Function;
import lombok.AllArgsConstructor;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@AllArgsConstructor
public class Graphing {
    private Function baseFunction;
    private double[] xData;


    public JPanel getChart(int width, int height){
        NewtonPolynomial polynomial = new NewtonPolynomial(baseFunction);
        List<Function> functions = polynomial.getPolynomial(xData);

        XYChart chart = new XYChart(width, height);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[xData.length - 1]);

        double[] yData = new double[xData.length];
        for(int i = 0; i < yData.length; i++)
            yData[i] = baseFunction.getValue(xData[i]);
        XYSeries points = chart.addSeries("Points", xData, yData);
        points.setMarker(SeriesMarkers.CIRCLE);
        points.setMarkerColor(Color.RED);
        points.setLineColor(Color.WHITE);

        double step = Math.abs(xData[xData.length - 1] - xData[0]) / width;
        double[] xGraphing = new double[width];
        double[] yBaseFunction = new double[width];
        for(int i = 0; i < yBaseFunction.length; i++){
            double arg = xData[0] + step * i;
            yBaseFunction[i] = baseFunction.getValue(arg);
            xGraphing[i] = arg;
        }

        double[] yPol = new double[width];
        for(int i = 0; i < width; i++){
            double arg = xGraphing[i];
            if(xData[0] + (Math.abs(xData[xData.length - 1] - xData[0]) / 2) > arg)
                yPol[i] = functions.get(0).getValue(arg);
            else
                yPol[i] = functions.get(1).getValue(arg);
        }
//        double[] xForward = new double[halfWidth];
//        double[] xBackward = new double[halfWidth];
//        double[] yPolynomialForward = new double[halfWidth];
//        double[] yPolynomialBackward = new double[halfWidth];
//        for(int i = 0; i < halfWidth; i++){
//            double arg1 = xData[0] + step * i;
//            double arg2 = arg1 + Math.abs(xData[xData.length - 1] - xData[0]) / 2;
//
//            xForward[i] = arg1;
//            xBackward[i] = arg2;
//            yPolynomialForward[i] = functions.get(0).getValue(arg1);
//            yPolynomialBackward[i] = functions.get(1).getValue(arg2);
//        }


        chart.addSeries("Исходная функция", xGraphing, yBaseFunction).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Интерполированная функции", xGraphing, yPol).
                setMarker(SeriesMarkers.NONE);
//        chart.addSeries("sas", xBackward, yPolynomialBackward).setMarker(SeriesMarkers.NONE);

        return new XChartPanel<>(chart);
    }
}
