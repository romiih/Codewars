package j6kyu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistTest {

    @Test
    void persistence() {
        assertEquals(0, Persist.persistence(11));
    }
}