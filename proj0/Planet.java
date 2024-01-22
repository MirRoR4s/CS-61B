public class Planet {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + (dy * dy));

    }

    /**
     * 返回行星 p 对当前行星的万有引力
     * @param p
     * @return p 对当前行星的万有引力
     */
    public double calcForceExertedBy(Planet p) {
        return (G * mass * p.mass) / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        return calcForceExertedBy(p) * dx / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double sumForce = 0;
        for (Planet p: planets) {
            if (p.equals(this)) {
                continue;
            }
            sumForce += calcForceExertedByX(p);
        }
        return sumForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sumForce = 0;
        for (Planet p: planets) {
            if (p.equals(this)) {
                continue;
            }
            sumForce += calcForceExertedByY(p);
        }
        return sumForce;
    }

    /**
     * 更新当前行星的速度和位置，当对当前行星水平和垂直方向施加两个持续时间为 dt 秒的力 fx 和 fy 时。
     * @param dt
     * @param fx
     * @param fy
     */
    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    /**
     * 在当前行星的位置绘制 imgFileName 图片
     */
    public void draw() {
        imgFileName = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgFileName);
        StdDraw.show();
    }


}
