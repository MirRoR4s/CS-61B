public class TestDraw {
    public static void main(String[] args) {
        testDraw();
    }

    private static void testDraw() {
        Planet planet = new Planet(
            1.4960e+11, 0.0000e+00, 0.0000e+00, 2.9800e+04, 5.9740e+24, "images/earth.gif"
        );
        planet.draw();
    }
}
