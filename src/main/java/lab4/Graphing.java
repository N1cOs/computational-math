package lab4;

import lombok.AllArgsConstructor;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
public class Graphing {

    private double[] xData;
    private double[] yData;

    public JPanel getChart(int width, int height){
        XYChart chart = new XYChart(width, height);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[xData.length - 1]);

        XYSeries nodes = chart.addSeries("Узлы", xData, yData);
        nodes.setMarkerColor(Color.RED);
        nodes.setLineColor(Color.BLUE);
        nodes.setMarker(SeriesMarkers.CIRCLE);

        return new XChartPanel<>(chart);



    }
}
