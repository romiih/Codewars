package j6kyu;

import java.util.stream.Stream;

class Persist {
    public static int persistence(long n) {
        return calc(0, n);
    }

    private static int calc(int cur, long num) {
        String s = String.valueOf(num);
        if (s.length() == 1) {
            return cur;
        }
        cur++;
        return calc(cur, multi(s));
    }

    private static long multi(String num) {
         return Stream.of(num.split(""))
                 .map(String::new)
                 .mapToLong(Long::parseLong)
                 .reduce((a,v) -> a * v)
                 .getAsLong();
    }
}