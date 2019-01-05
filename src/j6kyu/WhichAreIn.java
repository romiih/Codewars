package j6kyu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WhichAreIn {

    public static String[] inArray(String[] array1, String[] array2) {
        List<String> a2 = Arrays.asList(array2);
        return Stream.of(array1)
                .filter(a -> a2.stream().anyMatch(s -> s.contains(a)))
                .sorted()
                .toArray(String[]::new);
    }
}