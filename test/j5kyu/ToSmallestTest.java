package j5kyu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ToSmallestTest {
    private static void testing(long n, String res) {
        assertEquals(res,
                Arrays.toString(ToSmallest.smallest(n)));
    }

    @Test
    void smallest() {
        System.out.println("Basic Tests smallest");
        testing(261235, "[126235, 2, 0]");
        testing(209917, "[29917, 0, 1]");
        testing(285365, "[238565, 3, 1]");
        testing(269045, "[26945, 3, 0]");
        testing(296837, "[239687, 4, 1]");
        testing(199819884756L, "[119989884756, 4, 0]");
        // the first char is the biggest one
        testing(935855753L, "[358557539, 0, 8]");
        testing(901736257279467648L, "[17362572794676489, 0, 17]");
        testing(403296541436963072L, "[32496541436963072, 0, 3]");
        testing(105801077236383232L, "[10580177236383232, 6, 0]");
        // sequenced the same digit
        testing(204100073749188448L, "[20410073749188448, 4, 0]");
        testing(60006964680412208L, "[66964680412208, 0, 3]");
    }
}