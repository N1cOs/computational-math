package lab1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        GaussMethod method;
        FileOutputStream outputStream = new FileOutputStream("D:\\Documents\\Labs\\ComputationalMath\\src\\result.csv");
        PrintWriter printWriter = new PrintWriter(outputStream);
        for(int i  = 50; i < 2000; i+=50){
            method = new GaussMethod(Main.generateMatrix(i));
            long start = System.currentTimeMillis();
            double[][] triangularMatrix = method.getTriangularMatrix();
            method.getResult(triangularMatrix);
            long resultTime = System.currentTimeMillis() - start;
            printWriter.println(i + "\t" + resultTime);
            System.out.println(i);
        }
        printWriter.close();
    }
}
