package object.ghost;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Graphics;

import game.GameJpanel;
import object.map.MapObject;

public class GhostYellow extends Ghost {
    public int ghostYellow_X, ghostYellow_Y;

    public GhostYellow() {
        loadImage();
        setGhostYellowXY();
    }

    public void loadImage() {
        image = new ImageIcon("resources/images/ghost/ghostYellow.gif").getImage();
        imageRight = new ImageIcon("resources/images/ghost/ghostYellowRight.gif").getImage();
        imageLeft = new ImageIcon("resources/images/ghost/ghostYellowLeft.gif").getImage();
        imageUp = new ImageIcon("resources/images/ghost/ghostYellowUp.gif").getImage();
        imageDown = new ImageIcon("resources/images/ghost/ghostYellowDown.gif").getImage();
    }

    public void setGhostYellowXY() {
        MapObject map = GameJpanel.map;
        ghostYellow_X = map.WIDTH_BRICK * 9 + 2;
        ghostYellow_Y = map.HEIGHT_BRICK * 10 + 2;
    }

    public Rectangle getRectGhostYellow() {
        return new Rectangle(ghostYellow_X + 3, ghostYellow_Y + 3, getWidthImage() - 6, getHeightImage() - 6);
    }

    public void update() {
        if (dir == 0) {
            ghostYellow_Y -= ghostSpeed * 24;
            dir = 2;
        } else if (dir == r) {
            if (canMove(ghostYellow_X + ghostSpeed, ghostYellow_Y)) {
                ghostYellow_X += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == l) {
            if (canMove(ghostYellow_X - ghostSpeed, ghostYellow_Y)) {
                ghostYellow_X -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == d) {
            if (canMove(ghostYellow_X, ghostYellow_Y + ghostSpeed)) {
                ghostYellow_Y += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == u) {
            if (canMove(ghostYellow_X, ghostYellow_Y - ghostSpeed)) {
                ghostYellow_Y -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        }
    }

    public void draw(Graphics g) {
        if (dir == 0) {
            g.drawImage(image, ghostYellow_X, ghostYellow_Y, null);
        } else if (dir == r) {
            g.drawImage(imageRight, ghostYellow_X, ghostYellow_Y, null);
        } else if (dir == l) {
            g.drawImage(imageLeft, ghostYellow_X, ghostYellow_Y, null);
        } else if (dir == u) {
            g.drawImage(imageUp, ghostYellow_X, ghostYellow_Y, null);
        } else if (dir == d) {
            g.drawImage(imageDown, ghostYellow_X, ghostYellow_Y, null);
        }
    }
}
