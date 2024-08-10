/*
 * @Author: 黄建涛 
 * @Date: 2024-08-10 13:35:15 
 * @Last Modified by: 黄建涛
 * @Last Modified time: 2024-08-10 20:05:04
 */

 // https://gamedev.stackexchange.com/questions/82059/algorithm-for-procedural-2d-map-with-connected-paths
package byog.Core;

import java.util.Random;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Game {
    public class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private static final int MIN_ROOM_SIZE = 5; // The max width (or max height) of the room.
    private Random RANDOM;

    public Game(long seed) {
        RANDOM = new Random(seed);

    }

    private void initialWorld(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void createDungeon(TETile[][] tiles) {
        ter.initialize(WIDTH, HEIGHT);
        initialWorld(tiles); // 初始化瓦片世界
        splitAndCreateRooms(tiles, new Position(0, 0), WIDTH, HEIGHT);
        ter.renderFrame(tiles);
    }

    private void splitAndCreateRooms(TETile[][] tiles, Position position, int width, int height) {
        // 1. Choose a random direction: horizontal or vertical splitting.
        // 2. Choose a random position, x for vertical, y for horizontal.

        // NOTE: Don't be too close to the dungeon border, we must be able to place a
        // room inside each generated sub-dungeon.

        if (width <= MIN_ROOM_SIZE || height <= MIN_ROOM_SIZE) {
            createRoom(tiles, position, width, height);
            return;
        }
        boolean splitHorizontally = RandomUtils.bernoulli(RANDOM);

        if (splitHorizontally) { // horizontal splitting.
            int randomY = position.y + RandomUtils.uniform(RANDOM, height);

            Position randomPosition = new Position(position.x, randomY);

            splitAndCreateRooms(tiles, position, width, randomY); // The bottom half
            splitAndCreateRooms(tiles, randomPosition, width, height - randomY); // The top half
            // createCorridor(tiles, x, y + split, width, 1);
        }

        else { // vertical splitting
            int randomX = position.x + RandomUtils.uniform(RANDOM, width);
            Position randomPosition = new Position(randomX, position.y);

            splitAndCreateRooms(tiles, position, randomX, height); // The left half
            splitAndCreateRooms(tiles, randomPosition, width - randomX, height); // The right half
            // createCorridor(tiles, x + split, y, 1, height);
        }

    }

    // 创建房间
    private void createRoom(TETile[][] tiles, Position p, int width, int height) {
        int roomWidth = RandomUtils.uniform(RANDOM, width - 2) + 2;
        int roomHeight = RandomUtils.uniform(RANDOM, height - 2) + 2;

        int roomX = p.x + RandomUtils.uniform(RANDOM, width - roomWidth);
        int roomY = p.y + RandomUtils.uniform(RANDOM, height - roomHeight);

        for (int i = roomX; i < roomX + roomWidth; i++) {
            for (int j = roomY; j < roomY + roomHeight; j++) {
                tiles[i][j] = Tileset.FLOOR;
            }
        }
    }

    // 创建走廊
    private void createCorridor(TETile[][] tiles, int x, int y, int w, int h) {
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                tiles[i][j] = Tileset.FLOOR;
            }
        }
    }

    /**
     * Method used for playing a fresh game. The game should start from the main
     * menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will
     * be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The
     * game should
     * behave exactly as if the user typed these characters into the game after
     * playing
     * playWithKeyboard. If the string ends in ":q", the same world should be
     * returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should
     * return the same
     * world. However, the behavior is slightly different. After playing with
     * "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string
     * "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the
     * saved game.
     * 
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    public static void main(String[] args) {
        Game game = new Game(12345);
        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        game.createDungeon(randomTiles);
    }
}
