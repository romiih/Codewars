package j6kyu;

public class ProdFib {

    public static long[] productFib(long prod) {
        long[] result = new long[3];

        long max = 0;
        result[0] = fibonacci(0);
        result[1] = fibonacci(1);
        while (prod > max) {
            long r = fibonacci(result[1], result[0]);
            result[0] = result[1];
            result[1] = r;
            max = result[0] * result[1];
        }
        result[2] = prod == max ? 1 : 0;

        return result;
    }

    private static long fibonacci(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    private static long fibonacci(long prev1, long prev2) {
        return prev1 + prev2;
    }
}
