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

    public TERenderer initial(int width, int height) {
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        return ter;
    }

    public void initialFill(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** draw a hexagon in p.x and p.y with side length equal s*/
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        fillWithHexagon(world, p, s, t);
    }

    /** 绘制一行瓷砖 */
    private static void drawTile(TETile[][] world, Position p, TETile t, int sideLength, int nothingNum) {
            for (int i = 0; i < nothingNum; i++) {
                p.x += 1;
            }
            for (int i = 0; i < sideLength; i++) {
                world[p.x][p.y] = t;
                p.x += 1;
            }
            for (int i = 0; i < nothingNum; i++) {
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

    public static void drawHexagonWorld(TETile[][] world, Position p, int s, TETile t) {
                Position[] positions1 = calcPositionCol1(p, s);
                for (Position p1: positions1) {
                    addHexagon(world, p1, s, t);
            }
                positions1 = calcPositionCol2(p, s);
                for (Position p2: positions1) {
                    addHexagon(world, p2, s, Tileset.FLOWER);
                }
        }

//    private static Position[] caclPosition(Position p, int s) {
//        Position[] positions = new Position[7];
//    }

    private static Position[] calcPositionCol1(Position p, int s) {
        Position[] positions = new Position[3];
        int startX = p.x;
        int startY = p.y;
        positions[0] = new Position(startX, startY);
        for (int i = 1; i < 3; i++) {
            startY += calculateTotalRowNum(s);
            positions[i] = new Position(startX, startY);
        }
        return positions;
    }

    private static Position[] calcPositionCol2(Position p, int s) {
        Position[] positions = new Position[4];
        int maxSideLength = calcMaxSideLength(s);
        int startX = p.x + (maxSideLength - 2);
        int startY = p.y - s;
        positions[0] = new Position(startX, startY);
        for (int i = 1; i < 4; i++) {
            startY += calculateTotalRowNum(s);
            positions[i] = new Position(startX, startY);
        }
        return positions;
    }


    public static void main(String[] args) {
        TETile[][] world = new TETile[50][50];
        HexWorld hw = new HexWorld();
        TERenderer teRenderer = hw.initial(50, 50);
        hw.initialFill(world);
        int startX = 0;
        int startY = 20;
        int sideLength = 3;
        Position p = new Position(0, 20);
        int maxSideLength = calcMaxSideLength(sideLength);
//        Position p = new Position(startX, startY);
//        addHexagon(world, p, 3, Tileset.FLOWER);
//        Position p1 = new Position(startX, startY + 6);
//        Position p2 = new Position(startX, startY + 12);
//        addHexagon(world, p1, 3, Tileset.WALL);
//        addHexagon(world, p2, 3, Tileset.TREE);
//        // right
//        Position p3 = new Position(startX + maxSideLength - 2, startY - 3);
//        addHexagon(world, p3, 3, Tileset.WALL);
//        teRenderer.renderFrame(world);
        drawHexagonWorld(world, p, sideLength, Tileset.TREE);
        teRenderer.renderFrame(world);

    }

}
