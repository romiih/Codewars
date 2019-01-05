package j4kyu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntervalTest {

    @Test
    void sumIntervals() {
        assertEquals(0, Interval.sumIntervals(null));
        assertEquals(0, Interval.sumIntervals(new int[][]{}));
        assertEquals(0, Interval.sumIntervals(new int[][]{
                {2,2}, {5,5}
        }));

        assertEquals(3, Interval.sumIntervals(new int[][]{
                {1,2}, {3,5}
        }));

        assertEquals(7, Interval.sumIntervals(new int[][]{
                {1,4}, {3,6}, {2,8}
        }));
    }
}