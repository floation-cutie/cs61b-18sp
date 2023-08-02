package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;
import java.io.Serializable;
public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private static long seed;
    private static Random random = new Random(seed);
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        seed = parseToSeed(input);
        TETile[][] finalWorldFrame = null;

        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    private long parseToSeed(String input) {
        int len = input.length();
        long ans = 0;
        for (int i = 0; i < len; i ++) {
            char c = input.charAt(i);
            if (i == 0) {
                if (c == 'n' || c == 'N') {
                    continue;
                }
                else {
                    System.exit(0);
                }
            }
            if (c != 's' && c != 'S') {
                ans = 10 * ans + c - '0';
            } else {
                break;
            }
        }

        if (input.charAt(len - 2) == ':'
                &&
                ( input.charAt(len - 1) == 'Q'
                        || input.charAt(len - 1) == 'q' )) {
            saveWorld();
        }

        return ans;
    }

    private static void saveWorld() {

    }
}
