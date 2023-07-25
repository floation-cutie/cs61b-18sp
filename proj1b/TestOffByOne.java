import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiates
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(String.valueOf(true), offByOne.equalChars('a', 'b'));
        assertTrue(String.valueOf(true), offByOne.equalChars('&', '%'));
        assertFalse(String.valueOf(false), offByOne.equalChars('@', '%'));
    }
}
