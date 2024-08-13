package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     * Position 用于指明六边形左下角的坐标
     */
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 在瓦片世界中绘制一个六边形
     * 
     * @param world 瓦片世界
     * @param p     六边形左下角坐标
     * @param s     六边形的边长
     * @param t     具备特定样式的瓦片
     */
    public static void drawHexagon(TETile[][] world, Position p, int s, TETile t) {
        TERenderer teRenderer = initial(world); // 初始化一个全黑的瓦片世界
        drawHegagon(world, p, s, t);
        teRenderer.renderFrame(world);
    }

    private static void initial(TERenderer teRenderer, TETile[][] world) {
        teRenderer.initialize(world.length, world[0].length);
        initialFill(world);
    }

    /**初始时将世界全部填黑*/
    private static void initialFill(TETile[][] world) {
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** 绘制一行瓷砖 */
    private static void drawRowTile(TETile[][] world, Position p, TETile t, int sideLength, int nothingNum) {
            p.x += nothingNum;
            for (int i = 0; i < sideLength; i++) {
                world[p.x][p.y] = t;
                p.x += 1;
            }
            p.x += nothingNum;
    }

    /**计算六边形最大边长（最宽的部分）*/
    private static int calcMaxSideLength(int s) {
        return 3 * s - 2;
    }

    /**计算绘制六边形所需总行数*/
    private static int calculateTotalRowNum(int s) {
        return 2 * s;
    }

    private static int calcNothingNum(int maxSideLength, int currentSideLength) {
        return (maxSideLength - currentSideLength) / 2;
    }

    /** 填充六边形 */
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
            drawRowTile(world, p, t, currentSideLenth, nothingNum);
            p.x = x;
            p.y += 1;
            currentSideLenth += delta;
        }
    }

    /** 绘制一个六边形世界（总共 19 个六边形） */
    public static void drawHexagonWorld(TETile[][] world, Position p, int s, TETile t) {
        TERenderer teRenderer = new TERenderer();
        initial(teRenderer, world);
        int maxSideLength = calcMaxSideLength(s);
        int deltaX = maxSideLength - 2;
        int deltaY = 3;

        Position[] PositionCol1 = calcPosition(p, s, 3, p.x, p.y);
        Position[] PositionCol2 = calcPosition(p, s, 4, p.x + deltaX, p.y - deltaY);
        Position[] PositionCol3 = calcPosition(p, s, 5, p.x + (2 * deltaX), p.y - (2 * deltaY));
        Position[] PositionCol4 = calcPosition(p, s, 4, p.x + (3 * deltaX), p.y - deltaY);
        Position[] PositionCol5 = calcPosition(p, s, 3, p.x + (4 * deltaX), p.y);
        for (Position p1: PositionCol1) {
            fillWithHexagon(world, p1, s, t);
        }
        for (Position p2: PositionCol2) {
            fillWithHexagon(world, p2, s, Tileset.FLOWER);
        }
        for (Position p3: PositionCol3) {
            fillWithHexagon(world, p3, s, Tileset.MOUNTAIN);
        }
        for (Position p4: PositionCol4) {
            fillWithHexagon(world, p4, s, Tileset.GRASS);
        }
        for (Position p5: PositionCol5) {
            fillWithHexagon(world, p5, s, Tileset.SAND);
        }
        teRenderer.renderFrame(world);

    }
    private static Position[] calcPosition(Position p, int sideLength, int hexagonNum, int startX, int startY) {
        Position[] positions = new Position[hexagonNum];
        positions[0] = new Position(startPosition.x, startPosition.y);
        int startY = startPosition.y;
        for (int i = 1; i < hexagonNum; i++) {
            startY += calculateTotalRowNum(sideLength);
            positions[i] = new Position(startX, startY);
        }
        return positions;
    }
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
        int startX = 0;
        int startY = 20;
        int sideLength = 3;
        Position p = new Position(startX, startY);
//        addHexagon(world, p, sideLength, Tileset.FLOWER);
//        int maxSideLength = calcMaxSideLength(sideLength);
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

    }

}
