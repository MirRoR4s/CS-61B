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
    private static final Random random = new Random();

    public static void main(String[] args) {
        Rectangle rootRoom = new Rectangle(0, 0, 50, 50);
        Node rootNode = new Node(rootRoom);

        split(rootNode, MIN_ROOM_SIZE);

        List<Rectangle> rooms = new ArrayList<>();
        collectRooms(rootNode, rooms);

        List<int[]> corridors = new ArrayList<>();
        createCorridors(rootNode, corridors);
        TERenderer ter = new TERenderer();
        ter.initialize(50, 50);
        TETile[][] map = new TETile[50][50];
        printMap(rootRoom.width, rootRoom.height, rooms, corridors, map);
        ter.renderFrame(map);
    }

    private static void split(Node node, int minSize) {
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

    private static void collectRooms(Node node, List<Rectangle> rooms) {
        if (node.isLeaf()) {
            int roomWidth = random.nextInt(Math.max(1, node.room.width - MIN_ROOM_SIZE + 1)) + MIN_ROOM_SIZE;
            int roomHeight = random.nextInt(Math.max(1, node.room.height - MIN_ROOM_SIZE + 1)) + MIN_ROOM_SIZE;

            int roomX = random.nextInt(Math.max(1, node.room.width - roomWidth + 1)) + node.room.x;
            int roomY = random.nextInt(Math.max(1, node.room.height - roomHeight + 1)) + node.room.y;

            rooms.add(new Rectangle(roomX, roomY, roomWidth, roomHeight));
        } else {
            if (node.left != null) collectRooms(node.left, rooms);
            if (node.right != null) collectRooms(node.right, rooms);
        }
    }

    private static void createCorridors(Node node, List<int[]> corridors) {
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

            createCorridors(node.left, corridors);
            createCorridors(node.right, corridors);
        }
    }

    private static void printMap(int width, int height, List<Rectangle> rooms, List<int[]> corridors, TETile[][] map) {
        // char[][] map = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = Tileset.NOTHING;
            }
        }

        for (Rectangle room : rooms) {
            for (int i = room.y; i < room.y + room.height; i++) {
                for (int j = room.x; j < room.x + room.width; j++) {
                    map[i][j] = Tileset.FLOOR;
                }
            }
            // 墙壁
            for (int i = room.y - 1; i <= room.y + room.height; i++) {
                for (int j = room.x - 1; j <= room.x + room.width; j++) {
                    if (i >= 0 && i < height && j >= 0 && j < width && map[i][j] != Tileset.FLOOR) {
                        map[i][j] = Tileset.WALL;
                    }
                }
            }
        }

        for (int[] corridor : corridors) {
            int x1 = corridor[0], y1 = corridor[1], x2 = corridor[2], y2 = corridor[3];
            if (x1 == x2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[y][x1] = Tileset.FLOOR;
                }
            } else {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    map[y1][x] = Tileset.FLOOR;
                }
            }
            // 走廊墙壁
            for (int y = Math.min(y1, y2) - 1; y <= Math.max(y1, y2) + 1; y++) {
                for (int x = Math.min(x1, x2) - 1; x <= Math.max(x1, x2) + 1; x++) {
                    if (y >= 0 && y < height && x >= 0 && x < width && map[y][x] == Tileset.NOTHING) {
                        map[y][x] = Tileset.WALL;
                    }
                }
            }
        }

        // for (int i = 0; i < height; i++) {
        //     for (int j = 0; j < width; j++) {
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println();
        // }
    }
}

