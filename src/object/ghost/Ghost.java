package object.ghost;

import java.awt.Image;
import game.GameJpanel;
import java.awt.Rectangle;
import java.util.Random;

import object.map.MapObject;

public class Ghost {
    Image image, imageRight, imageLeft, imageUp, imageDown;
    Random randomGhost = new Random();
    int dir = 0, r = 1, l = 2, u = 3, d = 4;
    int ghostSpeed = 2;

    public int getWidthImage() {
        return image.getWidth(null);
    }

    public int getHeightImage() {
        return image.getHeight(null);
    }

    public Rectangle getRectGhost_NextXY(int nextX, int nextY) {
        MapObject map = GameJpanel.map;
        nextX = nextX - (map.WIDTH_BRICK - getWidthImage()) / 2;
        nextY = nextY - (map.HEIGHT_BRICK - getHeightImage()) / 2;
        return new Rectangle(nextX, nextY, map.WIDTH_BRICK, map.HEIGHT_BRICK);
    }

    public boolean canMove(int nextX, int nextY) {
        MapObject map = GameJpanel.map;
        for (int i = 0; i < map.brick.length; i++) {
            for (int j = 0; j < map.brick[0].length; j++) {
                // intersects:kiem tra va cham 2 hcn
                if (map.brick[i][j].getpositionLogic() == 0) {
                    if (getRectGhost_NextXY(nextX, nextY).intersects(map.brick[i][j].getRect())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
