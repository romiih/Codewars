package j4kyu;

import java.util.ArrayList;
import java.util.List;

public class Interval {

    public static int sumIntervals(int[][] param) {
        if (param == null || param.length == 0) return 0;

        return unionParam(param).stream().mapToInt(e -> e[1] - e[0]).sum();
    }

    private static List<int[]> unionParam(int[][] param) {
        List<int[]> list = new ArrayList<>();

        for (int[] p : param) {
            int[] x = list.stream().filter(e -> (e[0] <= p[0] && p[0] <= e[1]) || e[0] <= p[1] && p[1] <= e[1])
                    .findAny().orElse(null);
            if (x != null) {
                x[0] = Math.min(p[0], x[0]);
                x[1] = Math.max(p[1], x[1]);
            } else {
                list.add(new int[] {p[0], p[1]});
            }
        }

        return list;
    }
}
