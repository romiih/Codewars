package j5kyu;

import java.util.List;
import java.util.stream.Collectors;

public class ToSmallest {

    public static long[] smallest(long n) {
        System.out.println(n);
        long[] result = new long[3];
        String s = String.valueOf(n);

        int digit = s.chars().min().getAsInt();
        int lastIndex =  getLastIndexMod(s, (char)digit);

        // first one is the smallest
        if (lastIndex == 0) {
            // get secondary smallest
            digit = s.substring(1).chars().min().getAsInt();
            lastIndex =  s.lastIndexOf(Character.toString((char)digit));
            // and move to second position
            result[2] = 1L;

        // if the smallest is second char
        } else if (s.charAt(1) == (char)digit && (lastIndex == 1 || s.charAt(2) < s.charAt(0))) {
            // move the first char
            digit = s.charAt(0);
            lastIndex = 0;
            // to first larger than the first
            result[2] = getInsertPosition(s.substring(1), (char)digit) + 1;
        } else {
            // move to first position
            result[2] = lastIndex == 0 ? 1L : 0L;
        }
        // get earliest position
        result[1] = lastIndex;

        List<String> list = s.chars().mapToObj(c -> Character.toString((char)c)).collect(Collectors.toList());
        if (result[1] < result[2]) {
            list.remove((int)result[1]);
            list.add((int)result[2], Character.toString((char)digit));
        } else {
            list.add((int)result[2], Character.toString((char)digit));
            list.remove((int)result[1] + 1);
        }

        result[0] = Long.parseLong(list.stream().collect(Collectors.joining()));

        return result;
    }

    /**
     * Get last index but the same char is sequenced get the first
     * @param s
     * @param c
     * @return
     */
    private static int getLastIndexMod(String s, char c) {
        int last = s.lastIndexOf(c);

        char[] chars = s.toCharArray();
        for (int idx = last - 1; 0 <= idx; idx--) {
            if (s.charAt(idx) == c) {
                last = idx;
            } else {
                break;
            }
        }

        return last;
    }

    private static int getInsertPosition(String s, char c) {
        int result = s.length() - 1;
        for (int idx = 0; idx < s.length(); idx++) {
            if (c < s.charAt(idx)) {
                result = idx - 1;
                break;
            }
        }
        // reverse the same chars
        for (int idx = result; 0 <= idx; idx--) {
            if (s.charAt(idx) == c) {
                result = idx - 1;
            } else {
                break;
            }
        }

        return result;
    }
}