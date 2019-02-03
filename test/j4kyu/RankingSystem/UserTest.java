package j4kyu.RankingSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void test() {
        User user = new User();
        assertEquals(-8, user.rank);
        assertEquals(0, user.progress);

        progressTest(user, -7, 10, -8);

        System.out.println("2 higher level will add 90 progress");
        progressTest(user, -5, 0, -7);

        System.out.println("Don't throw any progress away");
        progressTest(user, -3, 60, -6);

        System.out.println("Couldn't get any progress when completed an easier activity");
        progressTest(user, -8, 60, -6);

        System.out.println("Same level will add 3 progress");
        progressTest(user, -6, 63, -6);

        System.out.println("1 lower level will add 1 progress");
        progressTest(user, -7, 64, -6);

        System.out.println("Reach to max level");
        progressTest(user, 8, 0, 8); // Add 16900 progress but stop counting progress

    }

    private void progressTest(User user, int rank, int expProgress, int expRank) {
        user.incProgress(rank); // will add no progress
        assertEquals(expProgress, user.progress);
        assertEquals(expRank, user.rank);
    }
}