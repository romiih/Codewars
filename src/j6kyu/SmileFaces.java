package j6kyu;

import java.util.*;
import java.util.regex.Pattern;

public class SmileFaces {

    public static int countSmileys(List<String> arr) {
        Pattern pattern = Pattern.compile("(:|;)(-|~)?(\\)|D)");

        return (int) arr.stream()
                .filter(s -> pattern.matcher(s).matches())
                .count();
    }
}