package hw3.hash;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import java.awt.*;

public class SimpleOomage implements Oomage {
  private static final double WIDTH = 0.01;
  private static final boolean USE_PERFECT_HASH = true;
  protected int red;
  protected int green;
  protected int blue;

  public SimpleOomage(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException();
    }
    if ((r % 5 != 0) || (g % 5 != 0) || (b % 5 != 0)) {
      throw new IllegalArgumentException("red/green/blue values must all be multiples of 5!");
    }
    red = r;
    green = g;
    blue = b;
  }

  public static SimpleOomage randomSimpleOomage() {
    int red = StdRandom.uniform(0, 51) * 5;
    int green = StdRandom.uniform(0, 51) * 5;
    int blue = StdRandom.uniform(0, 51) * 5;
    return new SimpleOomage(red, green, blue);
  }

  public static void main(String[] args) {
    System.out.println("Drawing 4 random simple Oomages.");
    randomSimpleOomage().draw(0.25, 0.25, 1);
    randomSimpleOomage().draw(0.75, 0.75, 1);
    randomSimpleOomage().draw(0.25, 0.75, 1);
    randomSimpleOomage().draw(0.75, 0.25, 1);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o.getClass() != this.getClass()) {
      return false;
    }
    SimpleOomage simpleOomage = (SimpleOomage) o;
    return this.red == simpleOomage.red
        && this.green == simpleOomage.green
        && this.blue == simpleOomage.blue;
  }

  @Override
  public int hashCode() {
    /*
       一种仅当 red、blue、green 都相同时 hashCode 才相同的算法如下：
       （算法来自 chatGPT：https://chatgpt.com/c/671df5f1-56b4-8011-8903-ba110551cfa4）

       1. red / 5、blue / 5 和 green / 5 的值范围均为 0 到 51，
          因此可以将它们看成一个 52 进制系统中的三个“位”。
       2. (red / 5) * 52 * 52 为最高位，表示 a 的位置。
       3. (blue / 5) * 52 表示中间位。
       4. (green / 5) 为最低位，表示 c 的位置。
    */
    if (!USE_PERFECT_HASH) {
      return red + green + blue;
    } else {
      return (red / 5) * 52 * 52 + (blue / 5) * 52 + (green / 5);
    }
  }

  @Override
  public void draw(double x, double y, double scalingFactor) {
    StdDraw.setPenColor(new Color(red, green, blue));
    StdDraw.filledSquare(x, y, WIDTH * scalingFactor);
  }

  public String toString() {
    return "R: " + red + ", G: " + green + ", B: " + blue;
  }
}
