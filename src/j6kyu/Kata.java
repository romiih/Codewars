package j6kyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kata {
    public static String createPhoneNumber(int[] numbers) {
        List<String> list = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.toList());
        return String.format("(%s) %s-%s",
                list.subList(0, 3).stream().collect(Collectors.joining()),
                list.subList(3, 6).stream().collect(Collectors.joining()),
                list.subList(6, 10).stream().collect(Collectors.joining()));
    }

    public static String high(String s) {

        final int maxValue = Arrays.stream(s.split(" "))
                .mapToInt(Kata::score)
                .max().orElse(-1);
        return Arrays.stream(s.split(" ")).sequential()
                .filter(str -> Kata.score(str) == maxValue)
                .findFirst().orElse("");
    }

    private static int score(String s) {
        return s.chars().map(c -> (int)c - (int)'a' + 1).sum();
    }

    public static String encrypt(final String text, final int n) {
        if (!isValid(text, n)) return text;

        String r = text;
        for (int cnt = 0; cnt < n; cnt++) {
            final String t = r;
            r = IntStream.range(0, t.length()).filter(i -> i % 2 == 1)
                        .mapToObj(idx -> String.valueOf(t.charAt(idx)))
                        .collect(Collectors.joining())
                    + IntStream.range(0, t.length()).filter(i -> i % 2 == 0)
                        .mapToObj(idx -> String.valueOf(t.charAt(idx)))
                        .collect(Collectors.joining());
        }

        return r;
    }

    public static String decrypt(final String encryptedText, final int n) {
        if (!isValid(encryptedText, n)) return encryptedText;

        String r = encryptedText;
        for (int idx = 0; idx < n; idx++) {
            final String t = r;
            int halfLen = t.length() / 2;  // 2nd chars range
            // 2nd chars
            char[] result = new char[t.length()];
            IntStream.range(0, halfLen)
                    .forEach(i -> {
                        result[i * 2] = t.charAt(i + halfLen);
                        result[i * 2 + 1] = t.charAt(i);  // 2nd
                    });
            // if odd
            if (t.length() % 2 == 1) {
                result[t.length() - 1] = t.charAt(t.length() - 1);
            }
            r = new String(result);
        }

        return r;
    }

    private static boolean isValid(String text, int n) {
        if (text == null || text.equals("")) return false;
        return n > 0;
    }


    public static double findUniq(double arr[]) {
        double first = arr[0];
        double second = arr[1];
        if (first != second) {
            return first == arr[2] ? second : first;
        }
        for (int idx = 2; idx < arr.length; idx++) {
            if (first != arr[idx]) return arr[idx];
        }
        return 0;
    }

}

