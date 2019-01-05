package j6kyu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractsTest {

    @Test
    void convertFrac() {
        long[][] lst;
        lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        assertEquals("(6,12)(4,12)(3,12)", Fracts.convertFrac(lst));
    }

    @Test
    void convertFrac2() {
        long[][] lst;
        lst = new long[][] { {69, 130}, {87, 1310}, {30, 40} };
        assertEquals("(18078,34060)(2262,34060)(25545,34060)", Fracts.convertFrac(lst));
    }

}