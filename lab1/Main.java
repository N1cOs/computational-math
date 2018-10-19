package lab1;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите количество неизвестных:");
            int size = Integer.parseInt(scanner.nextLine());
            if (size <= 0)
                throw new Exception();
            System.out.println("Введите file, если хотите считать данные из файла, или line" +
                    ", если хотите ввести данные через командную строку");
            String inType = scanner.nextLine();
            double[][] matrix;
            switch (inType) {
                case "file":
                    System.out.println("Введите путь к файлу:");
                    matrix = getMatrix(new FileInputStream(scanner.nextLine()), size);
                    break;
                case "line":
                    System.out.println("Введите коэффициенты при неизвестных и свободные члены через пробел:");
                    matrix = getMatrix(System.in, size);
                    break;
                default:
                    throw new IOException();
            }
            GaussMethod method = new GaussMethod(matrix);
            double[][] triangularMatrix = method.getTriangularMatrix();
            if(triangularMatrix == null)
                throw new Exception();
            System.out.println("Треугольная матрица:");
            for (double[] array : triangularMatrix)
                System.out.println(Arrays.toString(array));
            double[] result = method.getResult(triangularMatrix);
            if (result != null) {
                System.out.println("Результат: " + Arrays.toString(result));
                System.out.println("Невязка: " + Arrays.toString(method.getResidual(result)));
            } else
                System.out.println("Система несовместна или имеет бесконечное количество решений");
        } catch (FileNotFoundException e) {
            System.out.println("Введен некорректный путь к файлу\n");
        } catch (IOException e) {
            System.out.println("Введен некорректный способ ввода данных\n");
        } catch (Exception e) {
            System.out.println("Числа введены некорректно\n");
        }
    }

    private static double[][] getMatrix(InputStream inputStream, int size) {
        double[][] matrix = new double[size][size + 1];
        try (Scanner scanner = new Scanner(inputStream)) {
            for (int i = 0; i < size; i++) {
                String[] values = scanner.nextLine().trim().split("\\s+");
                for (int j = 0; j <= size; j++)
                    matrix[i][j] = Double.parseDouble(values[j]);
            }
        }
        return matrix;
    }

    private static double[][] generateMatrix(int n) {
        double[][] matrix = new double[n][n + 1];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++)
                matrix[i][j] = random.nextInt(1000);
        }
        return matrix;
    }
}
