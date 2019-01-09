package lab3;

import lab2.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class UserInterface {

    private Function baseFunction;

    private Function interpolateFunction;

    private JPanel graphicPanel;

    private double[] xData = {1, 2, 3, 4};


    public UserInterface(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public JFrame getMainFrame(int width, int height) {
        final int firstXValue = 4;
        final int xAmount = 5;
        final int graphicHeight = height * 7 / 10;

        JFrame jFrame = new JFrame("Lab 4");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        JPanel mainPanel = new JPanel(null);
        jFrame.setContentPane(mainPanel);

        graphicPanel = getGraphicPanel(width, graphicHeight);
        mainPanel.add(graphicPanel);

        JPanel controlPanel = new JPanel(new GridLayout(5, 1));
        controlPanel.setSize(width, height - graphicHeight);
        controlPanel.setLocation(0, graphicHeight);
        mainPanel.add(controlPanel);

        JPanel selectPanel = new JPanel();
        controlPanel.add(selectPanel);
        JLabel labelAmount = new JLabel("Выберите количество узлов интерполирования");
        selectPanel.add(labelAmount);

        JComboBox<Integer> selectedXAmount = new JComboBox<>();
        for (int i = firstXValue; i < firstXValue + xAmount; i++)
            selectedXAmount.addItem(i);
        selectPanel.add(selectedXAmount);

        AtomicReference<JPanel> argsPanel = new AtomicReference<>(generateButtons(4));
        controlPanel.add(argsPanel.get());

        selectedXAmount.addActionListener(e ->{
            //ToDo:repaint args panel
        });

        JPanel changePanel = new JPanel();
        controlPanel.add(changePanel);

        JLabel changeLabel = new JLabel("Введите узел, в котором нужно поменять значение функции:");
        changePanel.add(changeLabel);

        JTextField changeField = new JTextField(5);
        changePanel.add(changeField);

        JButton mainButton = new JButton("Интерполировать");
        mainButton.addActionListener(e -> {
            //ToDo:try to find easier approach to update graphics
            mainPanel.remove(graphicPanel);
            graphicPanel = getGraphicPanel(width, graphicHeight);
            mainPanel.add(graphicPanel);
            mainPanel.updateUI();
        });
        controlPanel.add(mainButton);

        JPanel findValuePanel = new JPanel();
        controlPanel.add(findValuePanel);

        JLabel findValueLabel = new JLabel("Введите значение х, в котором нужно найти значение функции");
        findValuePanel.add(findValueLabel);

        JTextField findValueField = new JTextField(3);
        findValuePanel.add(findValueField);

        JLabel valueLabel = new JLabel(String.format("f(%s)=%s", "?", "?"));


        JButton findValueButton = new JButton("Найти");
        findValuePanel.add(valueLabel);
        findValuePanel.add(findValueButton);
        findValueButton.addActionListener(e -> {
            double value = interpolateFunction.getValue(Double.parseDouble(findValueField.getText()));
            valueLabel.setText(String.format("f(%s)=%.3f", findValueField.getText(), value));
        });

        return jFrame;
    }
    
    private JPanel generateButtons(int argsAmount){
        JPanel argsPanel = new JPanel(new GridLayout(1, argsAmount));
        for (int i = 0; i < argsAmount; i++) {
            int index = i;
            JTextField xValue = new JTextField();
            xValue.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        if (!xValue.getText().equals(""))
                            xData[index] = Double.parseDouble(xValue.getText());
                    } catch (NumberFormatException e1) {
                        //ToDo:print error message
                    }
                }
            });
            argsPanel.add(xValue);
        }
        return argsPanel;
    }

    private JPanel getGraphicPanel(int width, int height) {
        NewtonPolynomial newtonPolynomial = new NewtonPolynomial(baseFunction);
        Arrays.sort(xData);
        Function interpolateFunction = newtonPolynomial.interpolate(xData);
        this.interpolateFunction = interpolateFunction;
        JPanel graphicPanel =  new Graphing(baseFunction, interpolateFunction, xData).
                getChart(width, height);
        graphicPanel.setLocation(0, 0);
        graphicPanel.setSize(width, height);
        return graphicPanel;
    }

    public void draw(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = getMainFrame(width, height);
            jFrame.setVisible(true);
        });
    }


}
