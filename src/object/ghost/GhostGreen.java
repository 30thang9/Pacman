package object.ghost;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Graphics;

import game.GameJpanel;
import object.map.MapObject;

public class GhostGreen extends Ghost {
    public int ghostGreen_X, ghostGreen_Y;

    public GhostGreen() {
        loadImage();
        setGhostGreenXY();
    }

    public void loadImage() {
        image = new ImageIcon("resources/images/ghost/ghostGreen.gif").getImage();
        imageRight = new ImageIcon("resources/images/ghost/ghostGreenRight.gif").getImage();
        imageLeft = new ImageIcon("resources/images/ghost/ghostGreenLeft.gif").getImage();
        imageUp = new ImageIcon("resources/images/ghost/ghostGreenUp.gif").getImage();
        imageDown = new ImageIcon("resources/images/ghost/ghostGreenDown.gif").getImage();
    }

    public void setGhostGreenXY() {
        MapObject map = GameJpanel.map;
        ghostGreen_X = map.WIDTH_BRICK * 8 + 2;
        ghostGreen_Y = map.HEIGHT_BRICK * 10 + 2;
    }

    public Rectangle getRectGhostGreen() {
        return new Rectangle(ghostGreen_X + 3, ghostGreen_Y + 3, getWidthImage() - 6, getHeightImage() - 6);
    }

    public void update() {
        if (dir == 0) {
            ghostGreen_X += ghostSpeed * 12;
            ghostGreen_Y -= ghostSpeed * 24;
            dir = 1;
        } else if (dir == r) {
            if (canMove(ghostGreen_X + ghostSpeed, ghostGreen_Y)) {
                ghostGreen_X += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == l) {
            if (canMove(ghostGreen_X - ghostSpeed, ghostGreen_Y)) {
                ghostGreen_X -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == d) {
            if (canMove(ghostGreen_X, ghostGreen_Y + ghostSpeed)) {
                ghostGreen_Y += ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        } else if (dir == u) {
            if (canMove(ghostGreen_X, ghostGreen_Y - ghostSpeed)) {
                ghostGreen_Y -= ghostSpeed;
            } else {
                dir = randomGhost.nextInt(1, 5);
            }
        }
    }

    public void draw(Graphics g) {
        if (dir == 0) {
            g.drawImage(image, ghostGreen_X, ghostGreen_Y, null);
        } else if (dir == r) {
            g.drawImage(imageRight, ghostGreen_X, ghostGreen_Y, null);
        } else if (dir == l) {
            g.drawImage(imageLeft, ghostGreen_X, ghostGreen_Y, null);
        } else if (dir == u) {
            g.drawImage(imageUp, ghostGreen_X, ghostGreen_Y, null);
        } else if (dir == d) {
            g.drawImage(imageDown, ghostGreen_X, ghostGreen_Y, null);
        }
    }
}
