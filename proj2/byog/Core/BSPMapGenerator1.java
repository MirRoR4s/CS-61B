package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean splitable(int minSize) {
        return width > minSize * 2 || height > minSize * 2;
    }

    public int centerX() {
        return x + width / 2;
    }

    public int centerY() {
        return y + height / 2;
    }
}

class Node {
    Rectangle room;
    Node left, right;

    public Node(Rectangle room) {
        this.room = room;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}

public class BSPMapGenerator {
    private static final int MIN_ROOM_SIZE = 5;
    private static final int MAX_ROOM_SIZE = 15;
    private Random random;
    private Node node;
    List<Rectangle> rooms;
    List<int[]> corridors;

    public BSPMapGenerator(Node node, long seed) {
        this.node = node;
        this.random = new Random(seed);
        rooms = new ArrayList<>();
        corridors = new ArrayList<>();
    }

    public void split(int minSize) {
        split(node, minSize);
        collectRooms(node);
        createCorridors(node);
    }

    private void split(Node node, int minSize) {
        if (!node.room.splitable(minSize)) {
            return;
        }

        boolean splitHorizontal = random.nextBoolean();

        if (node.room.width > node.room.height && node.room.width / node.room.height >= 1.25) {
            splitHorizontal = false;
        } else if (node.room.height > node.room.width && node.room.height / node.room.width >= 1.25) {
            splitHorizontal = true;
        }

        int max = (splitHorizontal ? node.room.height : node.room.width) - minSize;
        if (max <= minSize) {
            return;
        }

        int split = random.nextInt(max - minSize + 1) + minSize;

        Rectangle leftRoom, rightRoom;
        if (splitHorizontal) {
            leftRoom = new Rectangle(node.room.x, node.room.y, node.room.width, split);
            rightRoom = new Rectangle(node.room.x, node.room.y + split, node.room.width, node.room.height - split);
        } else {
            leftRoom = new Rectangle(node.room.x, node.room.y, split, node.room.height);
            rightRoom = new Rectangle(node.room.x + split, node.room.y, node.room.width - split, node.room.height);
        }

        node.left = new Node(leftRoom);
        node.right = new Node(rightRoom);

        split(node.left, minSize);
        split(node.right, minSize);
    }

    private void collectRooms(Node node) {
        if (node.isLeaf()) {
            int roomWidth = random.nextInt(Math.max(1, node.room.width - MIN_ROOM_SIZE + 1)) + MIN_ROOM_SIZE;
            int roomHeight = random.nextInt(Math.max(1, node.room.height - MIN_ROOM_SIZE + 1)) + MIN_ROOM_SIZE;

            int roomX = random.nextInt(Math.max(1, node.room.width - roomWidth + 1)) + node.room.x;
            int roomY = random.nextInt(Math.max(1, node.room.height - roomHeight + 1)) + node.room.y;

            rooms.add(new Rectangle(roomX, roomY, roomWidth, roomHeight));
        } else {
            if (node.left != null) collectRooms(node.left);
            if (node.right != null) collectRooms(node.right);
        }
    }

    private void createCorridors(Node node) {
        if (node.left != null && node.right != null) {
            int[] leftCenter = {node.left.room.centerX(), node.left.room.centerY()};
            int[] rightCenter = {node.right.room.centerX(), node.right.room.centerY()};

            if (random.nextBoolean()) {
                corridors.add(new int[]{leftCenter[0], leftCenter[1], rightCenter[0], leftCenter[1]});
                corridors.add(new int[]{rightCenter[0], leftCenter[1], rightCenter[0], rightCenter[1]});
            } else {
                corridors.add(new int[]{leftCenter[0], leftCenter[1], leftCenter[0], rightCenter[1]});
                corridors.add(new int[]{leftCenter[0], rightCenter[1], rightCenter[0], rightCenter[1]});
            }

            createCorridors(node.left);
            createCorridors(node.right);
        }
    }

    public void drawRoom(TETile[][] map) {
        for (Rectangle room : rooms) {
            int width = room.x + room.width;
            int height = room.y + room.height;

            for (int i = room.x; i < width; i++) {
                for (int j = room.y; j < height; j++) {
                    if (i == room.x || i == width - 1 || j == room.y || j == height - 1) {
                        map[i][j] = Tileset.WALL;
                    } else {
                        map[i][j] = Tileset.FLOOR;
                    }
                }
            }
        }

        for (int[] corridor : corridors) {
            int x1 = corridor[0], y1 = corridor[1], x2 = corridor[2], y2 = corridor[3];
            if (x1 == x2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[x1][y] = Tileset.FLOOR;
                    if (map[x1 - 1][y] == Tileset.NOTHING) map[x1 - 1][y] = Tileset.WALL;
                    if (map[x1 + 1][y] == Tileset.NOTHING) map[x1 + 1][y] = Tileset.WALL;
                }
            } else {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    map[x][y1] = Tileset.FLOOR;
                    if (map[x][y1 - 1] == Tileset.NOTHING) map[x][y1 - 1] = Tileset.WALL;
                    if (map[x][y1 + 1] == Tileset.NOTHING) map[x][y1 + 1] = Tileset.WALL;
                }
            }
        }
    }

    public static void main(String[] args) {
        int width = 50;
        int height = 50;
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Rectangle rootRoom = new Rectangle(0, 0, width, height);
        Node rootNode = new Node(rootRoom);
        BSPMapGenerator bspMapGenerator = new BSPMapGenerator(rootNode, 123456);
        int minRoomSize = 5;

        bspMapGenerator.split(minRoomSize);
        bspMapGenerator.drawRoom(world);

        ter.renderFrame(world);
    }
}
