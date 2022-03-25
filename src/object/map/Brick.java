package object.map;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Graphics;

import game.GameJpanel;

public class Brick {
    Image imageWall;
    Image imageFood;
    public int imageX, imageY;
    private boolean destroyed;// danh dau khi ve tung thuc an
    public int positionLogic;

    public Brick(int x, int y, int tp) {
        this.imageX = x;
        this.imageY = y;
        this.positionLogic = tp;
        destroyed = false;
        loadImage();
    }

    public void loadImage() {
        // imageWall = new ImageIcon("resources/images/map/wall.png").getImage();
        imageFood = new ImageIcon("resources/images/map/food.gif").getImage();
    }

    public int getpositionLogic() {
        return this.positionLogic;
    }

    public Image getImageWall() {
        return this.imageWall;
    }

    public void setImageWall(Image imageWall) {
        this.imageWall = imageWall;
    }

    public Image getImageFood() {
        return this.imageFood;
    }

    public void setImageFood(Image imageFood) {
        this.imageFood = imageFood;
    }

    public int getImageX() {
        return this.imageX;
    }

    public int getImageY() {
        return this.imageY;
    }

    public int getImageX(int x) {
        return this.imageX + x;
    }

    public int getImageY(int y) {
        return this.imageY + y;
    }

    public int getWallWidth() {
        return imageWall.getWidth(null);
    }

    public int getWallHeight() {
        return imageWall.getWidth(null);
    }

    public int getFoodWidth() {
        return imageFood.getWidth(null);
    }

    public int getFoodHeight() {
        return imageFood.getHeight(null);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean val) {
        destroyed = val;
    }

    public Rectangle getRect() {
        MapObject map = GameJpanel.map;
        return new Rectangle(imageX, imageY,
                map.WIDTH_BRICK, map.HEIGHT_BRICK);
    }

    public Rectangle getRectFood(int x, int y) {
        return new Rectangle(getImageX(x) + 3, getImageY(y) + 3,
                imageFood.getWidth(null) - 6, imageFood.getHeight(null) - 4);
    }

    public void draw(Graphics g) {
        g.drawImage(imageFood, getImageX(8), getImageY(8), null);
    }

}
