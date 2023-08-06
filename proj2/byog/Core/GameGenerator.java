package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import edu.princeton.cs.introcs.StdDraw;

import java.util.Random;



public class GameGenerator {

    static final int[] dx={0, 1, 0, -1, 1, -1, 1, -1};
    static final int[] dy={1, 0, -1, 0, 1, 1, -1, -1};
    private static Random r = new Random();
    public static class position{
        int x;
        int y;
        public position(int _x, int _y) {
            x = _x;
            y = _y;
        }

        @Override
        public boolean equals(Object obj) {
            position pos = (position) obj;
            return (this.x == pos.x && this.y == pos.y);
        }
    }


    public static void roomFirstGenerate(TETile[][] world, int offset) {
        BinarySpacePartitioning split  = new BinarySpacePartitioning();
        LinkedListDeque<BinarySpacePartitioning.room> roomsList = split.BSP(world,MapTest.minHeight, MapTest.minWidth);
        for (BinarySpacePartitioning.room room : roomsList) {
            for (int col = offset; col < room.width - offset; col++) {
                for (int row = offset; row < room.height - offset; row++) {
                    int xx = room.startPos.x + col;
                    int yy = room.startPos.y + row;
                    if (xx > 0 && yy > 0 && xx < MapTest.WIDTH && yy < MapTest.HEIGHT) {
                        world[xx][yy] = Tileset.FLOOR;
                    }
                }
            }
        }
        split.connectCorridors(world, roomsList);
    }

    /**
     * 进行随机游走,并调整地牢结构更紧凑
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

    public static void wallGenerate(TETile[][] world) {
        for (int i = 0; i < MapTest.WIDTH; i++) {
            for (int j = 0; j < MapTest.HEIGHT; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    for (int k = 0; k < 8; k++) {
                        int XX = i + dx[k];
                        int YY = j + dy[k];
                        if (XX >= 0 && XX < MapTest.WIDTH  && YY >= 0 && YY < MapTest.HEIGHT
                                && world[XX][YY] != Tileset.FLOOR) {
                            world[XX][YY] = Tileset.WALL;
                        }
                    }
                }

            }
        }
    }
    private static class Direction2D {


        public static position getRandomDirection(position prePos) {

            int res = RandomUtils.uniform(r,4);
            int desX = prePos.x + dx[res];
            int desY = prePos.y + dy[res];
            while (!(desX > 0 && desX < MapTest.WIDTH  && desY > 0 && desY < MapTest.HEIGHT)) {
                 res = RandomUtils.uniform(r,4);
                 desX = prePos.x + dx[res];
                 desY = prePos.y + dy[res];
            }
            return new position(desX, desY);
        }
    }
}
