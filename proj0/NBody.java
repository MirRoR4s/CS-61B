import java.util.ArrayList;
import java.util.List;

public class NBody {

    /**
     * 读取文件中的宇宙半径
     * @param fileName 文件名
     * @return 宇宙半径
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    /**
     * 读取文件中的多个行星
     * @param fileName 文件名
     * @return 包含多个行星的数组
     */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int planetNum = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[planetNum];
        int cnt = 0;
        while (!in.isEmpty()) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[cnt] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            cnt += 1;
        }
        return planets;
    }
    public static void main(String[] args) {
        // T 和 dt 是什么？
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        // 包含着行星数据的文本文件名
        String filename = args[2];
        // 宇宙半径
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        drawBackground(radius);
        for (Planet planet: planets) {
            planet.draw();
        }
    }

    /**
     * 以 radius 为比例绘制背景图
     * @param radius 比例
     */
    public static void drawBackground(double radius) {
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();

    }
}
