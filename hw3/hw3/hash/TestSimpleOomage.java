package hw3.hash;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;

public class TestSimpleOomage {

  /** Calls tests for SimpleOomage. */
  public static void main(String[] args) {
    jh61b.junit.textui.runClasses(TestSimpleOomage.class);
  }

  @Test
  public void testHashCodeDeterministic() {
    SimpleOomage so = SimpleOomage.randomSimpleOomage();
    int hashCode = so.hashCode();
    for (int i = 0; i < 100; i += 1) {
      assertEquals(hashCode, so.hashCode());
    }
  }

  @Test
  public void testHashCodePerfect() {
    /*
     * 1. 首先用一个数据结构保存所有可能的 Oomage 对象
     * 2. 遍历该数据结构，从中获取两个不同的对象，并判断它们的 hashCode 是否相等。
     */
    ArrayList<SimpleOomage> arrayList = new ArrayList<SimpleOomage>();
    for (int i = 0; i <= 255; i += 5) {
      for (int j = i + 5; j <= 255; j += 5) {
        for (int k = j + 5; k <= 255; k += 5) {
          SimpleOomage ooTmp = new SimpleOomage(i, j, k);
          arrayList.add(ooTmp);
        }
      }
    }

    for (int i = 0; i < arrayList.size(); i++) {
      for (int j = i + 1; j < arrayList.size(); j++) {
        SimpleOomage ooA = arrayList.get(i);
        SimpleOomage ooB = arrayList.get(j);
        assertNotEquals(ooA.toString() + "\n\t" + ooB.toString(), ooA.hashCode(), ooB.hashCode());
      }
    }
  }

  @Test
  public void testEquals() {
    SimpleOomage ooA = new SimpleOomage(5, 10, 20);
    SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
    SimpleOomage ooB = new SimpleOomage(50, 50, 50);
    assertEquals(ooA, ooA2);
    assertNotEquals(ooA, ooB);
    assertNotEquals(ooA2, ooB);
    assertNotEquals(ooA, "ketchup");
  }

  @Test
  public void testHashCodeAndEqualsConsistency() {
    SimpleOomage ooA = new SimpleOomage(5, 10, 20);
    SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
    HashSet<SimpleOomage> hashSet = new HashSet<>();
    hashSet.add(ooA);
    assertTrue(hashSet.contains(ooA2));
  }

  @Test
  public void testRandomOomagesHashCodeSpread() {
    List<Oomage> oomages = new ArrayList<>();
    int N = 10000;

    for (int i = 0; i < N; i += 1) {
      oomages.add(SimpleOomage.randomSimpleOomage());
    }

    assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
  }
}
