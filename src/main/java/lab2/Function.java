package lab2;


public interface Function {
    double getValue(double arg);

    double getValue(double x, double y);

    static int factorial(int n){
        if(n == 1 || n == 0)
            return 1;
        return factorial(n - 1) * n;
    }
}
