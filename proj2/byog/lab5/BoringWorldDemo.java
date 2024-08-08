package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 60; // 60 瓦片 tile 宽
    private static final int HEIGHT = 30; // 30 瓦片高

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT); // 定义一个 WIDTH 瓦片宽，HEIGHT 瓦片高的世界，注意到一个瓦片是 16 * 16 像素

        // initialize tiles
        // 给瓦片世界的每个瓦片一个默认样式，NOTHING 意味着全黑
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
                // world[x][y] = Tileset.FLOWER;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        // 给瓦片世界的部分区域增添光彩。
        for (int x = 21; x < 35; x += 1) {
            for (int y = 5; y < 10; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }

        // draws the world to the screen
        ter.renderFrame(world); // 渲染瓦片，这样在屏幕上就能看到它们了。
    }

}
