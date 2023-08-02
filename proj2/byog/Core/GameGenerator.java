package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import edu.princeton.cs.introcs.StdDraw;

import java.util.Random;

public class GameGenerator {

    private static Random r = new Random();
    public static class position{
        int x;
        int y;
        public position(int _x, int _y) {
            x = _x;
            y = _y;
        }

//        public static position positionCompound(position a, position b) {
//            return new position(a.x + b.x, a.y + b.y);
//        }
    }

    /**
     * 基于走廊优先的算法,进行随机游走,并调整地牢结构更紧凑
     * @param world 地图
     * @param curPos 游走起始坐标
     * @param walkLength 步长
     */
    public static void simpleRandomWalk(TETile[][] world, position curPos, int walkLength) {
        position prvPos = curPos;
        for (int i = 0; i < walkLength; i ++) {
            position newPos = Direction2D.getRandomDirection(prvPos);
            world[newPos.x][newPos.y] = Tileset.FLOOR;
            prvPos = newPos;
        }
    }

    private static class Direction2D {
        private static final int[] dx={0, 1, 0, -1};
        private static final int[] dy={1, 0, -1, 0};

        public static position getRandomDirection(position prePos) {

            int res = RandomUtils.uniform(r,4);
            int desX = prePos.x + dx[res];
            int desY = prePos.y + dy[res];
            while (!(desX > 0 && desX < MapTest.WIDTH + 4 && desY > 0 && desY < MapTest.HEIGHT + 4)) {
                 res = RandomUtils.uniform(r,4);
                 desX = prePos.x + dx[res];
                 desY = prePos.y + dy[res];
            }
            return new position(desX, desY);
        }
    }
}
