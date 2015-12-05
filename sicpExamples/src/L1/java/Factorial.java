package L1.java;


public class Factorial {


    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println(factorial.facItr(6));
    }

    private int facItr(int x) {
        Funct funct = new Funct() {
            @Override
            public int apply(int product, int counter) {
                if (counter > x) {
                    return product;
                }
                return apply((product * counter), ++counter);
            }
        };

        return funct.apply(1, 1);
    }

    private interface Funct {
        int apply(int product, int counter);
    }

    private int facRec(int x) {
        if (x < 1) {
            return 1;
        }
        return x * (facRec(--x));
    }
}
