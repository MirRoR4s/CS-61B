import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(cc.equalChars('a', 'f'));
        assertTrue(cc.equalChars('f', 'a'));
        assertFalse(cc.equalChars('f', 'h'));
        
    }
}
