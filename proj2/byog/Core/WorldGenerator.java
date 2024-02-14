package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private TETile[][] world;
    private Random random;

    public WorldGenerator(long seed) {
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        random = new Random(seed);
    }

    public void generate() {
        int numRooms = random.nextInt(10) + 1;
        for (int i = 0; i < numRooms; i++) {
            addRandomRoom();
        }
        int numHallways = random.nextInt(10) + 1;
        for (int i = 0; i < numHallways; i++) {
            addRandomHallway();
        }
    }

    public void addRandomRoom() {
        // 房间左下角横、纵坐标
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        // 房间宽高
        int width = random.nextInt(WIDTH - x);
        int height = random.nextInt(HEIGHT - y);
        addRoom(x, y, width, height);
    }

    public void addRoom(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
        
//        addDoor(x + width / 2, y + height / 2);
    }

    public void addDoor(int x, int y) {
        world[x][y] = Tileset.LOCKED_DOOR;
    }

    public void addRandomHallway() {
        int x1 = random.nextInt(WIDTH);
        int y1 = random.nextInt(HEIGHT);
        int x2 = random.nextInt(WIDTH);
        int y2 = random.nextInt(HEIGHT);
        addHallway(x1, y1, x2, y2);
    }

    public void addHallway(int x1, int y1, int x2, int y2) {
        // Add a simple straight hallway for simplicity
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            world[i][y1] = Tileset.FLOOR;
        }
        for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
            world[x2][j] = Tileset.FLOOR;
        }
    }

    public void render() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        WorldGenerator wg = new WorldGenerator(123456789);
        wg.generate();
        wg.render();
    }
}
