package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestNiceSpreadOomage {
  /** After you've written haveNiceHashCodeSpread, run this and it should pass. */
  @Test
  public void testRandomOomagesHashCodeSpread() {
    List<Oomage> oomages = new ArrayList<>();
    int N = 10000;

    for (int i = 0; i < N; i += 1) {
      oomages.add(NiceSpreadOomage.randomNiceSpreadOomage());
    }

    assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
  }

  private static class NiceSpreadOomage implements Oomage {
    private int val;

    public static NiceSpreadOomage randomNiceSpreadOomage() {
      NiceSpreadOomage x = new NiceSpreadOomage();
      x.val = StdRandom.uniform(0, 1000000);
      return x;
    }

    @Override
    public void draw(double x, double y, double scalingFactor) {
    }

    @Override
    public int hashCode() {
      return val;
    }
  }
}
