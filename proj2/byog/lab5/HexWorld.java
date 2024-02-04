package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static TERenderer initial(int width, int height) {
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        return ter;
    }

    private static void initialFill(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** draw a hexagon in p.x and p.y with side length equal s*/
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        TERenderer ter = initial(world.length, world[0].length);
        initialFill(world);
        fillWithHexagon(world, p, s, t);
        ter.renderFrame(world);
    }

    /** 绘制一行瓷砖 */
    private static void drawTile(TETile[][] world, Position p, TETile t, int sideLength, int nothingNum) {
            for (int i = 0; i < nothingNum; i++) {
                world[p.x][p.y] = Tileset.SAND;
                p.x += 1;
            }
            for (int i = 0; i < sideLength; i++) {
                world[p.x][p.y] = t;
                p.x += 1;
            }
            for (int i = 0; i < nothingNum; i++) {
                world[p.x][p.y] = Tileset.SAND;
                p.x += 1;
            }
    }


    private static int calcMaxSideLength(int s) {
        return 3 * s - 2;
    }

    private static int calculateTotalRowNum(int s) {
        return 2 * s;
    }

    private static int calcNothingNum(int maxSideLength, int currentSideLength) {
        return (maxSideLength - currentSideLength) / 2;
    }

    private static void fillWithHexagon(TETile[][] world, Position p, int s, TETile t) {
        int maxSideLength = calcMaxSideLength(s);
        int rowNum = calculateTotalRowNum(s);
        int x = p.x;
        int delta = 2;
        int currentSideLenth = s;
        for (int i = 0; i < rowNum; i++) {
            if (currentSideLenth > maxSideLength) {
                currentSideLenth = maxSideLength;
                delta = -2;
            }
            int nothingNum = calcNothingNum(maxSideLength, currentSideLenth);
            drawTile(world, p, t, currentSideLenth, nothingNum);
            p.x = x;
            p.y += 1;
            currentSideLenth += delta;
        }
    }
    public static void main(String[] args) {
        TETile[][] world = new TETile[50][50];
        Position p = new Position(0, 0);
        addHexagon(world, p, 3, Tileset.FLOWER);
    }

}
