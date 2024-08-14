package byog.Core;

import java.util.Random;

public class BSPTree {
    public class Dungeon {
        private int x;
        private int y;
        private int width;
        private int height;
    
        public Dungeon(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    
        /**
         * 表明当前 Dungeon 是否可以就分割
         * 
         * @param minSize
         * @return true，如果当前 Dungeon 还可以继续分割
         */
        public boolean splitable(int minSize) {
            return width > minSize * 2 || height > minSize * 2;
        }
    }

    private BSPTree leftChild;
    private BSPTree rightChild;
    private Dungeon dungeon;
    private final Random random;
    private final int MIN_ROOM_SIZE = 5;

    public BSPTree(int x, int y, int width, int height, int seed) {
        dungeon = new Dungeon(x, y, width, height);
        leftChild = null;
        rightChild = null;
        random = new Random(seed);
    }

    private BSPTree(Dungeon dungeon, Random random) {
        this.dungeon = dungeon;
        this.random = random;
        leftChild = null;
        rightChild = null;
        
    }

    public void split() {
        split(this);
    }
    // 构建 BSP 树
    private void split(BSPTree node) {

        // 如果当前 dungeon 的宽度或者高度接近房间的大小时停止分割
        if (!dungeon.splitable(MIN_ROOM_SIZE)) {
            return;
        }
        boolean splitHorizontal = RandomUtils.bernoulli(random);

        if (dungeon.width / dungeon.height >= 1.25) { // 垂直分割
            splitHorizontal = false;
        } else if (dungeon.height / dungeon.width >= 1.25) {
            splitHorizontal = true;
        }

        // 当前分割方向上的宽度或高度与最小房间大小之间的差值
        int difference = (splitHorizontal ? dungeon.height : dungeon.width) - MIN_ROOM_SIZE;
        // 如果这个差值没有超过2倍最小房间的大小的话，则不分割
        if (difference <= MIN_ROOM_SIZE) {
            return;
        }

        int position = RandomUtils.uniform(random, difference - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;

        Dungeon leftRoom;
        Dungeon rightRoom;
        if (splitHorizontal) {
            
            leftRoom = new Dungeon(node.room.x, node.room.y, node.room.width, position);
            rightRoom = new Dungeon(node.room.x, node.room.y + position, node.room.width, node.room.height - position);
        } else {
            leftRoom = new Rectangle(node.room.x, node.room.y, position, node.room.height);
            rightRoom = new Rectangle(node.room.x + position, node.room.y, node.room.width - position, node.room.height);
        }

        leftChild = new BSPTree(leftRoom);
        rightChild= new BSPTree(rightRoom);

        split(leftChild);
        split(rightChild);

    }

    // 打印 BSP 树
    public void show() {

    }

    public static void main(String[] args) {
        int width = 80;
        int height = 30;

    }

}
