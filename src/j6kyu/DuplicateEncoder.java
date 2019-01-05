package j6kyu;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateEncoder {
    static String encode(String word){
        Map<String, Long> map = word.chars()
                .map(Character::toLowerCase)
                .mapToObj(String::valueOf)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())
        );

        return word.chars()
                .map(Character::toLowerCase)
                .mapToObj(String::valueOf)
                .map(s -> map.get(s) > 1 ? ")" : "(")
                .collect(Collectors.joining());
    }
}
