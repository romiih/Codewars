package j4kyu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Bowling {

    private static String STRIKE = "X";
    private static String SPARE = "/";

    public static int bowling_score(String frames) {
        List<Frame> frameList = Arrays.stream(frames.split(" "))
                .map(Frame::new).collect(Collectors.toList());

        int frameLength = frameList.size();
        for (int idx = 0; idx < frameLength; idx++) {
            Frame n1 = idx + 1 < frameLength ? frameList.get(idx + 1) : null;
            Frame n2 = idx + 2 < frameLength ? frameList.get(idx + 2) : null;
            frameList.get(idx).followFrames(n1, n2);
        }

        return frameList.stream().mapToInt(Frame::getTotalPoint).sum();
    }

    private static class Frame {
        int[] points;
        int extra = 0;
        boolean strike = false;
        boolean spare = false;

        public Frame(String score) {
            String[] rolls = score.chars().mapToObj(c -> Character.toString((char)c)).toArray(String[]::new);
            points = IntStream.range(0, rolls.length).map(idx -> {
                String s = rolls[idx];
                if (s.equals(STRIKE)) return 10;
                if (s.equals(SPARE)) return 10 - Integer.valueOf(rolls[idx-1]);
                return Integer.valueOf(s);
            }).toArray();

            strike = score.contains(STRIKE);
            spare = score.contains(SPARE);
        }

        public void followFrames(Frame next1, Frame next2) {
            // Skip if last frame
            if (next1 == null && next2 == null) return;

            int seekRolls = strike ? 2 : spare ? 1 : 0;
            int[] n1 = next1 == null ? new int[]{} : next1.getPoints();
            int[] n2 = next2 == null ? new int[]{} : next2.getPoints();
            int[] followRolls = Stream.of(n1, n2).flatMapToInt(Arrays::stream).toArray();
            extra = IntStream.range(0, seekRolls).map(idx -> followRolls[idx]).sum();
        }
        public int[] getPoints() {
            return points;
        }
        public int getTotalPoint() {
            return Arrays.stream(points).sum() + extra;
        }
    }
}
