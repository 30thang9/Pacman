package object.ghost;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Graphics;

import game.GameJpanel;
import object.map.MapObject;

public class GhostRed extends Ghost {
    public int ghostRed_X, ghostRed_Y;

    public GhostRed() {
        loadImage();
        setGhostRedXY();
    }

    public void loadImage() {
        image = new ImageIcon("resources/images/ghost/ghostRed.gif").getImage();
        imageRight = new ImageIcon("resources/images/ghost/ghostRedRight.gif").getImage();
        imageLeft = new ImageIcon("resources/images/ghost/ghostRedLeft.gif").getImage();
        imageUp = new ImageIcon("resources/images/ghost/ghostRedUp.gif").getImage();
        imageDown = new ImageIcon("resources/images/ghost/ghostRedDown.gif").getImage();
    }

    public void setGhostRedXY() {
        MapObject map = GameJpanel.map;
        ghostRed_X = map.WIDTH_BRICK * 9 + 2;
        ghostRed_Y = map.HEIGHT_BRICK * 8 + 2;
    }

    public Rectangle getRectGhostRed() {
        return new Rectangle(ghostRed_X + 3, ghostRed_Y + 3, getWidthImage() - 6, getHeightImage() - 6);
    }

    public void update() {
        if (dir == 0) {
            dir = 2;
        } else if (dir == r) {
            if (canMove(ghostRed_X + ghostSpeed, ghostRed_Y)) {
                ghostRed_X += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == l) {
            if (canMove(ghostRed_X - ghostSpeed, ghostRed_Y)) {
                ghostRed_X -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == d) {
            if (canMove(ghostRed_X, ghostRed_Y + ghostSpeed)) {
                ghostRed_Y += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == u) {
            if (canMove(ghostRed_X, ghostRed_Y - ghostSpeed)) {
                ghostRed_Y -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        }
    }

    public void draw(Graphics g) {
        if (dir == 0) {
            g.drawImage(image, ghostRed_X, ghostRed_Y, null);
        } else if (dir == r) {
            g.drawImage(imageRight, ghostRed_X, ghostRed_Y, null);
        } else if (dir == l) {
            g.drawImage(imageLeft, ghostRed_X, ghostRed_Y, null);
        } else if (dir == u) {
            g.drawImage(imageUp, ghostRed_X, ghostRed_Y, null);
        } else if (dir == d) {
            g.drawImage(imageDown, ghostRed_X, ghostRed_Y, null);
        }
    }
}
