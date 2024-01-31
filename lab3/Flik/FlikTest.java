import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void TestIsSameNumber() {
        assertTrue(Flik.isSameNumber(1, 1));
        assertFalse(Flik.isSameNumber(1, 2));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", ArithmeticTest.class);
    }
}
