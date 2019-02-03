package j5kyu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class Tour {

    public static int tour(String[] arrFriends, String[][] ftwns, Map<String, Double> h) {
        if (ftwns.length == 0) return 0;
        System.out.println("arrFriends: " + Arrays.deepToString(arrFriends));
        System.out.println("ftwns: " + Arrays.deepToString(ftwns));
        System.out.println("h: " + h.toString());

        double distance = 0.0;
        double prevDist = 0.0;
        for (int idx = 0; idx < ftwns.length; idx++) {
            final String frind = ftwns[idx][0];
            if (Arrays.stream(arrFriends).noneMatch(f -> f.equals(frind))) continue;
            String address = ftwns[idx][1];
            double dist = h.get(address);
            distance += Math.sqrt(new BigDecimal(dist).pow(2)
                    .subtract(new BigDecimal(prevDist).pow(2)).doubleValue());

            System.out.println("prev:" + prevDist + ", dist:" + dist);
            prevDist = dist;
        }

        // last
        distance += prevDist;

        // your code
        return (int)distance;
    }
}
