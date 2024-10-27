package hw3.hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestComplexOomage {

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */
    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        // Your code here.
        /*
            这里要针对 hashCode 方法的缺陷特意构造一组会导致测试失败的输入
            16777216 * 256 = 0 上溢
            [1, 0, 0, 0] -> 16777216
            [1, 0, 0, 0, 10] -> 10

            1. 创建10个整型数组，分别包含 [1, 0, 0, 0, 10 * i]，i 是数组的序号，从1到10
            2. 用这10个整型数组初始化10个 Oomage
         */

        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> tmpList = new ArrayList<>();
            tmpList.add(1);
            tmpList.add(0);
            tmpList.add(0);
            tmpList.add(0);
            tmpList.add(10 * i);
            deadlyList.add(new ComplexOomage(tmpList));
        }


        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }
}
