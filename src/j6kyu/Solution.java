package j6kyu;

import java.util.stream.IntStream;

public class Solution {
    public int solution(int number) {

        return IntStream.range(0, number)
                .filter(idx -> idx % 3 == 0 || idx % 5 == 0)
                .sum();
    }
}
