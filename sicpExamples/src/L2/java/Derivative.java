package L2.java;


public class Derivative {
    private static double dx = 0.00001;

    public static void main(String[] args) {
        Derivative derivative = new Derivative();
        Function deriv = derivative.deriv(derivative.cube());


        System.out.println(deriv.apply(5.0));
    }

    private Function deriv(Function g) {
        return x -> (((g.apply(x + dx) - g.apply(x))) / dx);
    }

    private Function cube() {
        return x1 -> x1 * x1 * x1;
    }

    private interface Function {
        double apply(double x);
    }
}
