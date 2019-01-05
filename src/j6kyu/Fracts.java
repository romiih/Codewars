package j6kyu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Fracts {
    public static String convertFrac(long[][] lst) {
        List<long[]> list = Arrays.stream(lst)
                .map(Fracts::reduction)
                .collect(Collectors.toList());

        Long lcm = list.stream().map(l -> l[1]).reduce(1L, Fracts::lcm);

        return list.stream()
                .map(l -> new Long[] { l[0] * ( lcm / l[1]), lcm })
                .map(l -> String.format("(%d,%d)", l))
                .collect(Collectors.joining());
    }

    private static long gcd(long m, long n) {
        if(m < n) return gcd(n, m);
        if(n == 0) return m;
        return gcd(n, m % n);
    }

    private static long lcm(long m, long n) {
        return m * n / gcd(m, n);
    }

    private static long[] reduction(long[] frac) {
        long numer = frac[0];
        long denom = frac[1];

        long min = Math.min(numer, denom);
        long n = 1;
        for (long i = 2; i <= min; i++) {
            if (numer % i == 0 && denom % i == 0) {
                n = i;
            }
        }
        return new long[] { numer / n, denom / n };
    }
}
