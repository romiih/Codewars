package PathFinder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    void pathFinder() {

        String a = ".W.\n"+
                   ".W.\n"+
                   "...",

                b = ".W.\n"+
                    ".W.\n"+
                    "W..",

                c = "......\n"+
                    "......\n"+
                    "......\n"+
                    "......\n"+
                    "......\n"+
                    "......",

                d = "......\n"+
                    "......\n"+
                    "......\n"+
                    "......\n"+
                    ".....W\n"+
                    "....W.";

        assertEquals(4,  Finder.pathFinder(a));
        assertEquals(-1, Finder.pathFinder(b));
        assertEquals(10,  Finder.pathFinder(c));
        assertEquals(-1, Finder.pathFinder(d));
    }
}