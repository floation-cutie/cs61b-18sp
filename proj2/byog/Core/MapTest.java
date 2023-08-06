package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class MapTest {

    //private static int walkLength = 10;
    //private static int iterations = 2;
    static final int WIDTH = 50;
    static final int HEIGHT = 50;

    static int offset  = 2;
    static final int minWidth = 6;
    static final int minHeight = 6;
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        //GameGenerator.position startPos = new GameGenerator.position(20,20);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }


//        for (int i = 0; i < iterations; i++) {
//            GameGenerator.simpleRandomWalk(world, startPos, walkLength);
//        }
        GameGenerator.roomFirstGenerate(world, offset);
        GameGenerator.wallGenerate(world);
        ter.renderFrame(world);
    }
}
