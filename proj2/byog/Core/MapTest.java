package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class MapTest {

    private static int walkLength = 10;
    private static int iterations = 30;
    static final int WIDTH = 20;
    static final int HEIGHT = 20;
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH + 4, HEIGHT + 4);
        TETile[][] world = new TETile[WIDTH + 4][HEIGHT + 4];
        GameGenerator.position startPos = new GameGenerator.position(6,6);
        for (int x = 0; x < WIDTH + 4; x += 1) {
            for (int y = 0; y < HEIGHT + 4; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }


        for (int i = 0; i < iterations; i++) {
            GameGenerator.simpleRandomWalk(world, startPos, walkLength);
        }
        ter.renderFrame(world);
    }
}
