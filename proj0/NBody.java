
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
        // T 是总时间，dt 是时间增量
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        // 包含着行星数据的文本文件名
        String filename = args[2];
        // 宇宙半径
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        drawAnimation(T, dt, radius, planets);
    }

    /**
     * 以 radius 为比例绘制背景图
     * @param radius 比例
     */
    public static void drawBackground(double radius) {
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        // StdDraw.show();
    }

    /**
     * 绘制所有行星
     * @param planets 包含所有行星的数组
     */
    public static void drawPlanets(Planet[] planets) {
        for (Planet planet: planets) {
            planet.draw();
        }
    }

    /**
     * 创建动画
     */
    public static void drawAnimation(double T, double dt, double radius, Planet[] planets) {
        // 启用双缓冲
        StdDraw.enableDoubleBuffering();
        double t = 0.0;
        while (t < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            // 根据提示需要先计算完所有的力，再调用 update，所以此处分成两个 for 循环
            for (int i = 0; i < planets.length; i++) {
                // 计算对每个行星在 x 和 y 方向所受到的力
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                // 更新行星的位置、速度、加速度等
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            // 绘制背景图像
            drawBackground(radius);
            // 绘制所有行星
            drawPlanets(planets);
            // 显示
            StdDraw.show();
            // 将动画暂停 10 毫秒
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }
}
