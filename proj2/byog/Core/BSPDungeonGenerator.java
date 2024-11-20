package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class BSPDungeonGenerator {

    private int width;
    private int height;
    private int minRoomSize;

    private List<Rect> rooms;
    private List<Rect> corridors;
    TERenderer teRenderer;
    TETile[][] world;

    public BSPDungeonGenerator(int width, int height, int minRoomSize) {
        teRenderer = new TERenderer();
        teRenderer.initialize(width, height);
        world = new TETile[width][height];
        this.width = width;
        this.height = height;
        this.minRoomSize = minRoomSize;
        this.rooms = new ArrayList<>();
        this.corridors = new ArrayList<>();
        initial();
        generateDungeon();
        drawDungeon();
        teRenderer.renderFrame(world);
    }
    private void initial() {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }
    public static void main(String[] args) {
        new BSPDungeonGenerator(80, 50, 6);
    }

    private void generateDungeon() {
        Rect initialRoom = new Rect(0, 0, width, height);
        splitSpace(initialRoom);
    }

    private void splitSpace(Rect space) {
        Random random = new Random();

        if (space.width > minRoomSize * 2 || space.height > minRoomSize * 2) {
            if (space.width > space.height) {
                int splitX = random.nextInt(space.width - 2 * minRoomSize) + minRoomSize;
                Rect leftSpace = new Rect(space.x, space.y, splitX, space.height);
                Rect rightSpace = new Rect(space.x + splitX, space.y, space.width - splitX, space.height);
                splitSpace(leftSpace);
                splitSpace(rightSpace);
            } else {
                int splitY = random.nextInt(space.height - 2 * minRoomSize) + minRoomSize;
                Rect bottomSpace = new Rect(space.x, space.y, space.width, splitY);
                Rect topSpace = new Rect(space.x, space.y + splitY, space.width, space.height - splitY);
                splitSpace(bottomSpace);
                splitSpace(topSpace);
            }
        } else {
            rooms.add(space);
        }
    }

    private void drawDungeon() {
        for (Rect room : rooms) {
            for (int x = room.x; x < room.x + room.width; x++) {
                for (int y = room.y; y < room.y + room.height; y++) {
                    // 在此处放置绘制地板瓦片的逻辑
                    world[x][y] = Tileset.FLOOR;
                    System.out.println("Drawing floor tile at: (" + x + ", " + y + ")");
                }
            }
        }
    }

    class Rect {
        int x, y, width, height;

        Rect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
}
