package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
public class BinarySpacePartitioning{
    private Random r = new Random(138);

    /**
     * one implement is to from the center of the room to find other room
     * @param world 待渲染的地图
     * @param roomsList 房间列表
     */
    public void connectCorridors(TETile[][] world, LinkedListDeque<room> roomsList) {
        LinkedListDeque<position> roomCenter = new LinkedListDeque<>();
        for (room curRoom : roomsList) {
            position centerPos = new position(
                    curRoom.width / 2 + curRoom.startPos.x,
                    curRoom.height / 2 + curRoom.startPos.y);
            roomCenter.addLast(centerPos);
        }
        position curPos = roomCenter.removeFirst();
        while (!roomCenter.isEmpty()) {
            position nearestPos = findNearest(roomCenter,curPos);
            createCorridors(curPos, nearestPos, world);
            roomCenter.remove(nearestPos);
            curPos = nearestPos;
        }
    }

    private void createCorridors(position curPos, position nearestPos, TETile[][] world) {
        int xx = curPos.x;
        int yy = curPos.y;
        while (yy != nearestPos.y) {
            if (yy > nearestPos.y) {
                yy -= 1;
            }else {
                yy += 1;
            }
            world[xx][yy] = Tileset.FLOOR;
        }
        while (xx != nearestPos.x) {
            if (xx > nearestPos.x) {
                xx -= 1;
            }else {
                xx += 1;
            }
            world[xx][yy] = Tileset.FLOOR;
        }
    }

    private position findNearest(LinkedListDeque<position> roomCenter, position curPos) {
        double min = 0x3f3f3f3f;
        position nearPos = new position(0,0);
        for (position pos : roomCenter) {
            if (calculateDisSquare(pos, curPos) < min) {
                nearPos = pos;
                min = calculateDisSquare(pos, curPos);
            }
        }
        return nearPos;
    }

    private double calculateDisSquare(position a, position b){
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }
    public static class position{
        int x;
        int y;
        public position(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    public static class room{
        public position startPos;
        public int height;
        public int width;
        public room( position _startPos, int _height, int _width){
            startPos = _startPos;
            height = _height;
            width = _width;
        }
    }

    public LinkedListDeque<room> BSP(TETile[][] world, int minHeight, int minWidth) {
        LinkedListDeque<room> splitRoom = new LinkedListDeque<>();
        LinkedListDeque<room> roomList = new LinkedListDeque<>();
        room worldRoom = this.convertWorldtoRoom(world);
        splitRoom.addLast(worldRoom);
        //用一个队列维护
        while (!splitRoom.isEmpty()) {
            room curRoom = splitRoom.removeFirst();
            //分割条件,一直进行操作直到某一部分不再分割
            if (curRoom.width >= minHeight && curRoom.height >= minWidth) {
                if (r.nextDouble() < 0.5f) {
                    //水平分割,若不满足条件就竖直分割
                    if (curRoom.height >= 2 * minHeight) {
                        splitHorizontally(minHeight, curRoom, splitRoom);
                    } else if (curRoom.width >= 2 * minWidth) {
                        splitVertically(minWidth,curRoom, splitRoom);
                    } else if (r.nextDouble() < 0.8f){
                        roomList.addLast(curRoom);
                    }
                } else {
                    if (curRoom.width >= 2 * minWidth) {
                        splitVertically(minWidth,curRoom, splitRoom);
                    } else if (curRoom.height >= 2 * minHeight) {
                        splitHorizontally(minHeight, curRoom, splitRoom);
                    } else if (r.nextDouble() < 0.8f){
                        roomList.addLast(curRoom);
                    }
                }
            }
        }
        return roomList;
    }

    private void splitVertically(int minWidth, room curRoom, LinkedListDeque<room> splitRoom) {
        int splitX = RandomUtils.uniform(r, 1, curRoom.width);
//        if (splitX < minWidth || curRoom.width - splitX < minWidth) {
//            splitX = RandomUtils.uniform(r, 1, curRoom.width);
//        }
        room room1 = new room(
                new position(curRoom.startPos.x, curRoom.startPos.y),
                curRoom.height,
                splitX);
        room room2 = new room(
                new position(curRoom.startPos.x + splitX, curRoom.startPos.y),
                curRoom.height,
                curRoom.width - splitX);
        splitRoom.addLast(room1);
        splitRoom.addLast(room2);
    }

    private void splitHorizontally(int minHeight, room curRoom, LinkedListDeque<room> splitRoom) {
        int splitY = RandomUtils.uniform(r, 1, curRoom.height);
//        if (splitY < minHeight || curRoom.height - splitY < minHeight) {
//            splitY = RandomUtils.uniform(r, 1, curRoom.height);
//        }
        room room1 = new room(
                new position(curRoom.startPos.x, curRoom.startPos.y),
                splitY,
                curRoom.width);
        room room2 = new room(
                new position(curRoom.startPos.x, curRoom.startPos.y + splitY),
                curRoom.height - splitY,
                curRoom.width);
        splitRoom.addLast(room1);
        splitRoom.addLast(room2);
    }

    public room convertWorldtoRoom(TETile[][] world) {
        return new room(new position(0, 0), world.length, world[0].length);
    }
}