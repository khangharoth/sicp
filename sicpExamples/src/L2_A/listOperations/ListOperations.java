package L2_A.listOperations;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListOperations {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        System.out.println(list.stream().map(x -> x * x * x).collect(Collectors.toList()));
    }
}
