package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


class Rectangle {
    int x, y, width, height; // 矩形区域左下角坐标以及宽度和高度

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean splitable(int minSize) {
        return width > minSize * 2 || height > minSize * 2;
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
    private static final int MIN_ROOM_SIZE = 5; // 最小房间大小
    private static final int MAX_ROOM_SIZE = 15; // 最大房间大小
    private Random random;
    private Node node;

    public BSPMapGenerator(Node node, long seed) {
        this.node = node;
        this.random = new Random(seed);
    }
    
    public void split(int minSize) {
        split(node, minSize);
    }
    /**
     * 分割
     */
    private void split(Node node, int minSize) {
        if (!node.room.splitable(minSize)) {
            return;
        }
        // 默认情况下随机决定水平分割还是垂直分割
        boolean splitHorizontal = RandomUtils.bernoulli(random);

        if (node.room.width > node.room.height && node.room.width / node.room.height >= 1.25) {
            splitHorizontal = false;
        } else if (node.room.height > node.room.width && node.room.height / node.room.width >= 1.25) {
            splitHorizontal = true;
        }

        int max = (splitHorizontal ? node.room.height : node.room.width) - minSize;
        if (max <= minSize) {
            return;
        }

        // int split = random.nextInt(max - minSize + 1) + minSize;
        int split = RandomUtils.uniform(random, max - minSize + 1) + minSize;

        Rectangle leftRoom, rightRoom;
        if (splitHorizontal) { // 水平分割
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

    public void collectRooms(Node node, List<Rectangle> rooms) {
        if (node.isLeaf()) {
            int roomWidth = random.nextInt(Math.max(1, node.room.width - MIN_ROOM_SIZE + 1)) + MIN_ROOM_SIZE;
            int roomHeight = random.nextInt(Math.max(1, node.room.height - MIN_ROOM_SIZE + 1)) + MIN_ROOM_SIZE;

            int roomX = random.nextInt(Math.max(1, node.room.width - roomWidth + 1)) + node.room.x;
            int roomY = random.nextInt(Math.max(1, node.room.height - roomHeight + 1)) + node.room.y;

            rooms.add(new Rectangle(roomX, roomY, roomWidth, roomHeight));
        } else {
            if (node.left != null)
                collectRooms(node.left, rooms);
            if (node.right != null)
                collectRooms(node.right, rooms);
        }
    }

    private void printMap(int width, int height, List<Rectangle> rooms, TETile[][] map) {

        for (Rectangle room : rooms) {
            for (int i = room.y; i < room.y + room.height; i++) {
                for (int j = room.x; j < room.x + room.width; j++) {
                    map[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    public static void main(String[] args) {
        int width = 80; // 世界的宽度
        int height = 50; // 世界的高度
        // 初始化渲染引擎
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        // 初始化世界
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        
        BSPMapGenerator bspMapGenerator = new BSPMapGenerator(123456);

        Rectangle rootRoom = new Rectangle(0, 0, width, height); // 根节点的初始大房间
        Node rootNode = new Node(rootRoom);

        bspMapGenerator.split(rootNode, MIN_ROOM_SIZE);

        List<Rectangle> rooms = new ArrayList<>();
        collectRooms(rootNode, rooms);
        printMap(rootRoom.width, rootRoom.height, rooms, world);
        ter.renderFrame(world);
    }
}
