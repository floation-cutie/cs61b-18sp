package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static int size = 3;
    // The width and height of the whole huge hexagon
    private static final int WIDTH = 11 * size - 6; // 3 * longest line + 2 * shortest line: 3 * ((size - 1) * 2 + size) + 2 * size
    private static final int HEIGHT = 10 * size;    // 5 * size is the height of the huge hexagon

    private static final long SEED = 123356;

    /**
     * instantiate a random class for use
     * */
    private static Random RANDOM = new Random(SEED);

    public static class position{
        int x;
        int y;
        public position(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    public static void addRow(TETile[][] world, position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            //System.out.print(xCoord);
            //System.out.println(yCoord);
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }
    public static void addSingleHexagon(TETile[][] world, position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(s, yi);
            position rowStartP = new position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }

    public static void drawHexWorld(TETile[][] world, position p, TETile t) {

        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        int curRow = p.y;
        int lastRow = curRow;
        int curColumn = p.x;
        int reverseCurrColumn = WIDTH - 2 * (size - 1);
        int rowInterval = 2 * size;
        int columnInterval = 2 * size - 1;
        for (int num = 3; num < 6; num += 1) {
            for (int i = 0; i < num; i++) {
//                if (num == 5) {
//                    position curPos = new position(curColumn, curRow);
//                    t = randomTileType();
//                    addSingleHexagon(world, curPos, size, t);
//                    curRow += rowInterval;
//                    continue;
//                }
                position curPos = new position(curColumn, curRow);
                position revCurPos = new position(reverseCurrColumn, curRow);
                t = randomTileType();
                addSingleHexagon(world, curPos, size, t);
                t = randomTileType();
                addSingleHexagon(world, revCurPos, size, t);
                curRow += rowInterval;
            }
            curRow = lastRow - size;
            lastRow = curRow;
            curColumn += columnInterval;
            reverseCurrColumn -= columnInterval;
        }
    }
    private static TETile randomTileType() {
        int randomNum = RANDOM.nextInt(9);
        switch (randomNum) {
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.MOUNTAIN;
            case 3:
                return Tileset.UNLOCKED_DOOR;
            case 4:
                return Tileset.LOCKED_DOOR;
            case 5:
                return Tileset.SAND;
            case 6:
                return Tileset.TREE;
            case 7:
                return Tileset.GRASS;
            case 8:
                return Tileset.WATER;
            default:
                return Tileset.WALL;
        }
    }
    public static void main(String [] args) {
        TERenderer ter = new TERenderer();

        ter.initialize(WIDTH + 4, HEIGHT + 4);
        TETile[][] hexagonTiles = new TETile[WIDTH + 4][HEIGHT + 4];
        TETile randomT = randomTileType();
        position startPos = new position(size, 2 * size);
        for (int x = 0; x < WIDTH + 4; x += 1) {
            for (int y = 0; y < HEIGHT + 4; y += 1) {
                hexagonTiles[x][y] = Tileset.NOTHING;
            }
        }
        drawHexWorld(hexagonTiles, startPos, randomT);
        ter.renderFrame(hexagonTiles);
    }
}
