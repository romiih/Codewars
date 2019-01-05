package j6kyu;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindOdd {
    public static int findIt(int[] a) {
        Map<Integer, Long> map = Arrays.stream(a)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return map.entrySet().stream()
                .filter(e -> e.getValue() % 2 == 1)
                .collect(Collectors.toList())
                .get(0).getKey();
    }
}
