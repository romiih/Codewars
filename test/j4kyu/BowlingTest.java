package j4kyu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {

    @Test
    void bowling_score() {
        System.out.println("Maybe this bowler should put bumpers on...\n ");
        assertEquals(20, Bowling.bowling_score("11 11 11 11 11 11 11 11 11 11"));

        System.out.println("Woah! Perfect game!");
        assertEquals(300, Bowling.bowling_score("X X X X X X X X X XXX"));
    }
}