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
     * 绘制一个六边形
     * 
     * @param world 瓦片世界
     * @param p     六边形左下角坐标
     * @param s     六边形大小
     * @param t     瓦片样式
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        // 一个大小为 s 的六边形，其行数为 2s，中间两行的列数是 s + 2 * (s - 1) = 3s - 2
        TERenderer teRenderer = new TERenderer();
        initialWorld(teRenderer, world);
        fillWithHexagon(world, p, s, t);
        teRenderer.renderFrame(world);
    }

    private static void initialWorld(TERenderer teRenderer, TETile[][] world) {
        teRenderer.initialize(world.length, world[0].length);
        /** 初始时将世界全部填黑 */
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** 填充六边形 */
    private static void fillWithHexagon(TETile[][] world, Position p, int s, TETile t) {
        int maxColumnLength = getMaxColumnLength(s);
        int totalRow = getTotalRow(s);
        int x = p.x;
        int delta = 2;

        int currentColumnLength = s;
        
        for (int row = 0; row < totalRow; row++) {

            if (currentColumnLength > maxColumnLength) { // 超过了最大列长，开始绘制上半部分
                currentColumnLength = maxColumnLength;
                delta = -2;
            }
            int nothingNum = getNothingQuantity(maxColumnLength, currentColumnLength);
            drawRowTile(world, p, t, currentColumnLength, nothingNum);
            p.x = x;
            p.y += 1;
            currentColumnLength += delta;
        }
    }

    /** 获取六边形的最大列长（中间两行占用的瓦片数量） */
    private static int getMaxColumnLength(int s) {
        return 3 * s - 2;
    }

    /** 获取六边形总行数 */
    private static int getTotalRow(int s) {
        return 2 * s;
    }

    /**
     * 获取 Nothing 样式瓦片的数量
     * @param maxColumnLength
     * @param currentColumnLength
     * @return
     */
    private static int getNothingQuantity(int maxColumnLength, int currentColumnLength) {
        return (maxColumnLength - currentColumnLength) / 2;
    }

    /** 绘制一行瓷砖 */
    private static void drawRowTile(TETile[][] world, Position p, TETile t, int currentColumnLength, int nothingQuantity) {
        p.x += nothingQuantity; // 横坐标平移，这部分对应的是 Nothing 样式瓦片，默认已存在
        
        for (int i = 0; i < currentColumnLength; i++, p.x++) {
            world[p.x][p.y] = t;
        }

        p.x += nothingQuantity;
    }

    /** 绘制一个六边形世界（总共 19 个六边形） */
    public static void drawHexagonWorld(TETile[][] world, Position p, int s, TETile t) {
        TERenderer teRenderer = new TERenderer();
        initialWorld(teRenderer, world);
        int maxSideLength = getMaxColumnLength(s);
        int deltaX = maxSideLength - 2;
        int deltaY = 3;

        Position[] PositionCol1 = calcPosition(p, s, 3, p.x, p.y);
        Position[] PositionCol2 = calcPosition(p, s, 4, p.x + deltaX, p.y - deltaY);
        Position[] PositionCol3 = calcPosition(p, s, 5, p.x + (2 * deltaX), p.y - (2 * deltaY));
        Position[] PositionCol4 = calcPosition(p, s, 4, p.x + (3 * deltaX), p.y - deltaY);
        Position[] PositionCol5 = calcPosition(p, s, 3, p.x + (4 * deltaX), p.y);
        for (Position p1 : PositionCol1) {
            fillWithHexagon(world, p1, s, t);
        }
        for (Position p2 : PositionCol2) {
            fillWithHexagon(world, p2, s, Tileset.FLOWER);
        }
        for (Position p3 : PositionCol3) {
            fillWithHexagon(world, p3, s, Tileset.MOUNTAIN);
        }
        for (Position p4 : PositionCol4) {
            fillWithHexagon(world, p4, s, Tileset.GRASS);
        }
        for (Position p5 : PositionCol5) {
            fillWithHexagon(world, p5, s, Tileset.SAND);
        }
        teRenderer.renderFrame(world);

    }

    private static Position[] calcPosition(Position p, int sideLength, int hexagonNum, int startX, int startY) {
        Position[] positions = new Position[hexagonNum];
        positions[0] = new Position(startX, startY);
        for (int i = 1; i < hexagonNum; i++) {
            startY += getTotalRow(sideLength);
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
            startY += getTotalRow(s);
            positions[i] = new Position(startX, startY);
        }
        return positions;
    }

    private static Position[] calcPositionCol2(Position p, int s) {
        Position[] positions = new Position[4];
        int maxSideLength = getMaxColumnLength(s);
        int startX = p.x + (maxSideLength - 2);
        int startY = p.y - s;
        positions[0] = new Position(startX, startY);
        for (int i = 1; i < 4; i++) {
            startY += getTotalRow(s);
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
        // addHexagon(world, p, sideLength, Tileset.FLOWER);
        // int maxSideLength = calcMaxSideLength(sideLength);
        // Position p = new Position(startX, startY);
        // addHexagon(world, p, 3, Tileset.FLOWER);
        // Position p1 = new Position(startX, startY + 6);
        // Position p2 = new Position(startX, startY + 12);
        // addHexagon(world, p1, 3, Tileset.WALL);
        // addHexagon(world, p2, 3, Tileset.TREE);
        // // right
        // Position p3 = new Position(startX + maxSideLength - 2, startY - 3);
        // addHexagon(world, p3, 3, Tileset.WALL);
        // teRenderer.renderFrame(world);
        drawHexagonWorld(world, p, sideLength, Tileset.TREE);

    }

}
