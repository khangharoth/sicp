package L2.java;


public class AvgDamp {
    public static void main(String[] args) {
        AvgDamp damp = new AvgDamp();

        System.out.println(damp.avgDamp(x -> x * x).apply(10));
    }

    private Function avgDamp(Function f1) {
       return x -> average(x, f1.apply(x));
    }

    private int average(int x, int y) {
        return (x + y) / 2;
    }

    private interface Function {
        int apply(int x);
    }
}
