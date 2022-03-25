package object.ghost;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Graphics;

import game.GameJpanel;
import object.map.MapObject;

public class GhostPink extends Ghost {
    public int ghostPink_X, ghostPink_Y;

    public GhostPink() {
        loadImage();
        setGhostPinkXY();
    }

    public void loadImage() {
        image = new ImageIcon("resources/images/ghost/ghostPink.gif").getImage();
        imageRight = new ImageIcon("resources/images/ghost/ghostPinkRight.gif").getImage();
        imageLeft = new ImageIcon("resources/images/ghost/ghostPinkLeft.gif").getImage();
        imageUp = new ImageIcon("resources/images/ghost/ghostPinkUp.gif").getImage();
        imageDown = new ImageIcon("resources/images/ghost/ghostPinkDown.gif").getImage();
    }

    public void setGhostPinkXY() {
        MapObject map = GameJpanel.map;
        ghostPink_X = map.WIDTH_BRICK * 10 + 2;
        ghostPink_Y = map.HEIGHT_BRICK * 10 + 2;
    }

    public Rectangle getRectGhostPink() {
        return new Rectangle(ghostPink_X + 3, ghostPink_Y + 3, getWidthImage() - 6, getHeightImage() - 6);
    }

    public void update() {
        if (dir == 0) {
            ghostPink_X -= ghostSpeed * 12;
            ghostPink_Y -= ghostSpeed * 24;
            dir = 1;
        } else if (dir == r) {
            if (canMove(ghostPink_X + ghostSpeed, ghostPink_Y)) {
                ghostPink_X += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == l) {
            if (canMove(ghostPink_X - ghostSpeed, ghostPink_Y)) {
                ghostPink_X -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == d) {
            if (canMove(ghostPink_X, ghostPink_Y + ghostSpeed)) {
                ghostPink_Y += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == u) {
            if (canMove(ghostPink_X, ghostPink_Y - ghostSpeed)) {
                ghostPink_Y -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        }
    }

    public void draw(Graphics g) {
        if (dir == 0) {
            g.drawImage(image, ghostPink_X, ghostPink_Y, null);
        } else if (dir == r) {
            g.drawImage(imageRight, ghostPink_X, ghostPink_Y, null);
        } else if (dir == l) {
            g.drawImage(imageLeft, ghostPink_X, ghostPink_Y, null);
        } else if (dir == u) {
            g.drawImage(imageUp, ghostPink_X, ghostPink_Y, null);
        } else if (dir == d) {
            g.drawImage(imageDown, ghostPink_X, ghostPink_Y, null);
        }
    }
}
