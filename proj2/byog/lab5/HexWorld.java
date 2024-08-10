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

    private static TERenderer initial(TETile[][] world) {
        TERenderer teRenderer = new TERenderer();
        teRenderer.initialize(world.length, world[0].length);

        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return teRenderer;
    }

    /** 填充六边形 */
    private static void drawHegagon(TETile[][] world, Position p, int sideLength, TETile t) {
        // 从六边形的左下角开始绘制，先绘制下半部分，再绘制上半部分。
        int maxWidth = getMaxWidth(sideLength);
        int height = getHeight(sideLength);
        int x = p.x;
        int delta = 2; // 六边形宽度增量
        int currentWidth = sideLength;

        for (int i = 0; i < height; i++) {
            if (currentWidth > maxWidth) { // 超过最大宽度，开始绘制六边形上半部分
                currentWidth = maxWidth;
                delta = -2;
            }
            int nothingNum = getNothingNum(maxWidth, currentWidth);
            drawRowTile(world, p, t, currentWidth, nothingNum);
            p.x = x;
            p.y += 1;
            currentWidth += delta;
        }
    }

    /** 获取六边形的最大宽度（也就是六边形中间两行最长的部分所占用的瓦片数量） */
    private static int getMaxWidth(int sideLength) {
        return 3 * sideLength - 2;
    }

    /** 获取六边形的高度 */
    private static int getHeight(int sideLength) {
        return 2 * sideLength;
    }

    /** 获取指定宽度下 `Nothing` 样式瓦片的数量 */
    private static int getNothingNum(int maxWidth, int currentWidth) {
        return (maxWidth - currentWidth) / 2;
    }

    /** 在指定行上填充一列具有特定样式的瓦片 */
    private static void drawRowTile(TETile[][] world, Position p, TETile t, int currentWidth, int nothingNum) {
        p.x += nothingNum;
        for (int i = 0; i < currentWidth; i++) {
            world[p.x][p.y] = t;
            p.x += 1;
        }
        p.x += nothingNum;
    }

    /** 绘制一个六边形世界（总共 19 个六边形） */
    public static void drawHexagonWorld(TETile[][] world, Position p, int s) {
        // 纵向地看待这 19 个边长为 3 的六边形，可以发现其由 5 列组成，每列由不同数量的六边形组成。
        // 为了绘制出这 19 个六边形组成的图形，我们可以逐列地进行绘制。
        TERenderer teRenderer = initial(world);
        int maxWidth = getMaxWidth(s);
        int deltaX = maxWidth - 2;
        int deltaY = 3;
        int[] hexagonsNum = new int[] { 3, 4, 5, 4, 3 }; // 每一列六边形的数量
        Position[] starPositions = new Position[] { // 每一列六边形最下方的那个六边形的左下角坐标
                new Position(p.x, p.y),
                new Position(p.x + deltaX, p.y - deltaY),
                new Position(p.x + (2 * deltaX), p.y - (2 * deltaY)),
                new Position(p.x + (3 * deltaX), p.y - deltaY),
                new Position(p.x + (4 * deltaX), p.y)
        };

        TETile[] teTilesColumn1 = new TETile[] { Tileset.GRASS, Tileset.GRASS, Tileset.MOUNTAIN }; // 第一列六边形的瓦片样式
        TETile[] teTilesColumn2 = new TETile[] { Tileset.FLOWER, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.GRASS };
        TETile[] teTilesColumn3 = new TETile[] { Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.MOUNTAIN,
                Tileset.TREE };

        TETile[] teTilesColumn4 = new TETile[] { Tileset.MOUNTAIN, Tileset.TREE, Tileset.SAND, Tileset.FLOWER };

        TETile[] teTilesColumn5 = new TETile[] { Tileset.SAND, Tileset.TREE, Tileset.FLOWER };

        TETile[][] totalTiles = new TETile[][] { teTilesColumn1, teTilesColumn2, teTilesColumn3, teTilesColumn4,
                teTilesColumn5 };
        drawHexagons(world, s, hexagonsNum, starPositions, totalTiles); // 逐列绘制六边形
        teRenderer.renderFrame(world);

    }

    private static void drawHexagons(TETile[][] world, int sideLength, int[] hexagonsNum, Position[] startPositions,
            TETile[][] totalTiles) {
        for (int i = 0; i < 5; i++) {
            Position[] positions = calcPositions(sideLength, hexagonsNum[i], startPositions[i]);

            for (int j = 0; j < positions.length; j++) {

                drawHegagon(world, positions[j], sideLength, totalTiles[i][j]);
            }

        }

    }

    private static Position[] calcPositions(int sideLength, int hexagonNum, Position startPosition) {
        Position[] positions = new Position[hexagonNum];
        positions[0] = new Position(startPosition.x, startPosition.y);
        int startY = startPosition.y;
        for (int i = 1; i < hexagonNum; i++) {
            startY += getHeight(sideLength);
            positions[i] = new Position(startPosition.x, startY);
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
        drawHexagonWorld(world, p, sideLength);

    }

}
