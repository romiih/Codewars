package j6kyu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KataTest {
    @Test
    void high() {
        assertEquals("taxi", Kata.high("man i need a taxi up to ubud"));
        assertEquals("volcano", Kata.high("what time are we climbing up to the volcano"));
        assertEquals("semynak", Kata.high("take me to semynak"));
        assertEquals("ppvau", Kata.high("ppvau ftphir"));
        assertEquals("ftphir", Kata.high("ftphir ppvau"));
        assertEquals("sslixpkvbbnyckgf", Kata.high("sslixpkvbbnyckgf qlzeccwcssvyv qlzeccwcssvyv"));
        assertEquals("qlzeccwcssvyv", Kata.high("qlzeccwcssvyv sslixpkvbbnyckgf qlzeccwcssvyv"));
    }

    @Test
    public void testEncrypt() {
        // assertEquals("expected", "actual");
        assertEquals("This is a test!", Kata.encrypt("This is a test!", 0));
        assertEquals("hsi  etTi sats!", Kata.encrypt("This is a test!", 1));
        assertEquals("s eT ashi tist!", Kata.encrypt("This is a test!", 2));
        assertEquals(" Tah itse sits!", Kata.encrypt("This is a test!", 3));
        assertEquals("This is a test!", Kata.encrypt("This is a test!", 4));
        assertEquals("This is a test!", Kata.encrypt("This is a test!", -1));
        assertEquals("hskt svr neetn!Ti aai eyitrsig", Kata.encrypt("This kata is very interesting!", 1));
    }
    @Test
    public void testDecrypt() {
        // assertEquals("expected", "actual");
        assertEquals("This is a test!", Kata.decrypt("This is a test!", 0));
        assertEquals("This is a test!", Kata.decrypt("hsi  etTi sats!", 1));
        assertEquals("This is a test!", Kata.decrypt("s eT ashi tist!", 2));
        assertEquals("This is a test!", Kata.decrypt(" Tah itse sits!", 3));
        assertEquals("This is a test!", Kata.decrypt("This is a test!", 4));
        assertEquals("This is a test!", Kata.decrypt("This is a test!", -1));
        assertEquals("This kata is very interesting!", Kata.decrypt("hskt svr neetn!Ti aai eyitrsig", 1));
    }
}