package byog.Core;

import java.util.HashSet;
import java.util.Random;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RandomWalkDungeonGenerator {
    public class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int width;
    public int height;
    public int walkLength;
    public Random random;
    public HashSet<Position> positions;

    public RandomWalkDungeonGenerator(int width, int height, int walkLength, long seed) {
        this.width = width;
        this.height = height;
        this.walkLength = walkLength;
        random = new Random(seed);
        positions = new HashSet<>();

    }

    public void generateDungeon() {
        Position currentPos = new Position(width / 2, height / 2);
        positions.add(currentPos);

        for (int i = 0; i < walkLength; i++) {
            System.out.println("current posisiton" + currentPos.x + " " + currentPos.y);
            currentPos = getRandomPosition(currentPos);
            positions.add(currentPos);

        }
    }
    public Position getRandomPosition(Position position) {
        int randomIndex = RandomUtils.uniform(random, 4);
        switch (randomIndex) {
            case 0: // up
                return new Position(position.x, position.y + 1);
            case 1: // down
                return new Position(position.x, position.y - 1);
            case 2: // left
                return new Position(position.x - 1, position.y);
            case 3:
                return new Position(position.x + 1, position.y);
            default:
                return new Position(position.x, position.y + 1);
        }
    }
    public void show() {
        for (Position position: positions) {
            System.out.println(position.x + " " + position.y);
        }
    }

    public static void main(String[] args) {
        int width = 80;
        int height = 50;
        TETile[][] world = new TETile[width][height];
        TERenderer teRenderer = new TERenderer();
        teRenderer.initialize(width, height);
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

        int walkLength = 40;
        long seed = 9999;
        RandomWalkDungeonGenerator randomWalkDungeonGenerator = new RandomWalkDungeonGenerator(width, height, walkLength, seed);
        randomWalkDungeonGenerator.generateDungeon();
        for (Position p: randomWalkDungeonGenerator.positions) {
            world[p.x][p.y] = Tileset.FLOOR;
        }
        teRenderer.renderFrame(world);
    }

}
