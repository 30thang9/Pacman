package object.pacman;

import java.awt.Image;
import javax.swing.ImageIcon;

import game.GameJpanel;
import object.map.MapObject;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Pacman {
    private Image pacman, pacmanUp, pacmanDown, pacmanLeft, pacmanRight, pacmanDeath;
    public int pacmanX, pacmanY;// vi tri
    public int pacmanSpeed = 2;// buoc nhay
    public boolean goUp = false, goDown = false, goLeft = false, goRight = false;
    public int enterKeyCode = 0;
    public static int currentKeyCode = 0; // bien nhan gia tri cua key right:1,left:2,up:3,down:4

    public Pacman() {
        loadImage();
        setPacmanXY();
    }

    public void loadImage() {
        pacman = new ImageIcon("resources/images/pacman/pacman.png").getImage();
        pacmanUp = new ImageIcon("resources/images/pacman/pacmanUp1.gif").getImage();
        pacmanDown = new ImageIcon("resources/images/pacman/pacmanDown1.gif").getImage();
        pacmanLeft = new ImageIcon("resources/images/pacman/pacmanLeft1.gif").getImage();
        pacmanRight = new ImageIcon("resources/images/pacman/pacmanRight1.gif").getImage();
        pacmanDeath = new ImageIcon("resources/images/pacman/pacmanDeath.gif").getImage();
    }

    public int getpacmanX() {
        return this.pacmanX;
    }

    public void setpacmanX(int pacmanX) {
        this.pacmanX = pacmanX;
    }

    public int getpacmanY() {
        return this.pacmanY;
    }

    public void setpacmanY(int pacmanY) {
        this.pacmanY = pacmanY;
    }

    public Image getPacman() {
        return this.pacman;
    }

    public void setPacman(Image pacman) {
        this.pacman = pacman;
    }

    public Image getPacmanUp() {
        return this.pacmanUp;
    }

    public void setPacmanUp(Image pacmanUp) {
        this.pacmanUp = pacmanUp;
    }

    public Image getPacmanDown() {
        return this.pacmanDown;
    }

    public void setPacmanDown(Image pacmanDown) {
        this.pacmanDown = pacmanDown;
    }

    public Image getPacmanLeft() {
        return this.pacmanLeft;
    }

    public void setPacmanLeft(Image pacmanLeft) {
        this.pacmanLeft = pacmanLeft;
    }

    public Image getPacmanRight() {
        return this.pacmanRight;
    }

    public void setPacmanRight(Image pacmanRight) {
        this.pacmanRight = pacmanRight;
    }

    public int getWidthPacman() {
        return pacman.getWidth(null);
    }

    public int getHeightPacman() {
        return pacman.getHeight(null);
    }

    public Rectangle getRect_Pacman() {
        return new Rectangle(pacmanX + 3, pacmanY + 3,
                getWidthPacman() - 6, getHeightPacman() - 6);
    }

    public Rectangle getRect_Pacman_nextXY(int nextX, int nextY) {
        MapObject map = GameJpanel.map;
        nextX = nextX - (map.WIDTH_BRICK - getWidthPacman()) / 2;
        nextY = nextY - (map.HEIGHT_BRICK - getHeightPacman()) / 2;
        return new Rectangle(nextX, nextY, map.WIDTH_BRICK, map.HEIGHT_BRICK);
    }

    public void setPacmanXY() {
        MapObject map = GameJpanel.map;
        pacmanX = map.WIDTH_BRICK * 9 + 2;
        pacmanY = map.HEIGHT_BRICK * 16 + 2;
    }

    public void printPosition() {
        System.out.println(pacmanX + " " + pacmanY);
    }

    public void update() {
        if (goUp && canMove(pacmanX, pacmanY - pacmanSpeed)) {
            pacmanY -= pacmanSpeed;
            printPosition();
        } else if (goDown && canMove(pacmanX, pacmanY + pacmanSpeed)) {
            pacmanY += pacmanSpeed;
            printPosition();
        } else if (goLeft && canMove(pacmanX - pacmanSpeed, pacmanY)) {
            pacmanX -= pacmanSpeed;
            printPosition();
        } else if (goRight && canMove(pacmanX + pacmanSpeed, pacmanY)) {
            pacmanX += pacmanSpeed;
            printPosition();
        }
    }

    public boolean canMove(int nextX, int nextY) {
        MapObject map = GameJpanel.map;
        for (int i = 0; i < map.brick.length; i++) {
            for (int j = 0; j < map.brick[0].length; j++) {
                // intersects:kiem tra va cham 2 hcn
                if (map.brick[i][j].getpositionLogic() == 0) {
                    if (getRect_Pacman_nextXY(nextX, nextY).intersects(map.brick[i][j].getRect())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean cantMove(int nextX, int nextY) {
        MapObject map = GameJpanel.map;
        for (int i = 0; i < map.brick.length; i++) {
            for (int j = 0; j < map.brick[0].length; j++) {
                // intersects:kiem tra va cham 2 hcn
                if (map.brick[i][j].getpositionLogic() == 0) {
                    if (getRect_Pacman_nextXY(nextX, nextY).intersects(map.brick[i][j].getRect())) {
                        return true;
                    }
                }
            }
        }
        currentKeyCode = 0;
        return false;
    }

    public void draw(Graphics g) {
        MapObject map = GameJpanel.map;
        if (!GameJpanel.running) {
            enterKeyCode = 0;
            g.drawImage(pacman, pacmanX, pacmanY, null);
        }
        if (pacmanX > map.WIDTH_BRICK * map.colsMap) {
            pacmanX = 2;
            g.drawImage(pacmanRight, pacmanX, pacmanY, null);
            enterKeyCode = 1;
        } else if (pacmanX < 0) {
            pacmanX = map.WIDTH_BRICK * (map.colsMap - 1) + 2;
            g.drawImage(pacmanLeft, pacmanX, pacmanY, null);
            enterKeyCode = 2;
        } else {
            if (/* right */enterKeyCode == 1 && canMove(pacmanX + pacmanSpeed, pacmanY)) {
                g.drawImage(pacmanRight, pacmanX, pacmanY, null);
                goUp = false;
                goDown = false;
                goLeft = false;
                goRight = true;
                currentKeyCode = 1;
            } else if (/* left */enterKeyCode == 2
                    && canMove(pacmanX - pacmanSpeed, pacmanY)) {
                g.drawImage(pacmanLeft, pacmanX, pacmanY, null);
                goUp = false;
                goDown = false;
                goRight = false;
                goLeft = true;
                currentKeyCode = 2;
            } else if (/* up */enterKeyCode == 3
                    && canMove(pacmanX, pacmanY - pacmanSpeed)) {
                g.drawImage(pacmanUp, pacmanX, pacmanY, null);
                goRight = false;
                goDown = false;
                goLeft = false;
                goUp = true;
                currentKeyCode = 3;
            } else if (/* down */enterKeyCode == 4
                    && canMove(pacmanX, pacmanY + pacmanSpeed)) {
                g.drawImage(pacmanDown, pacmanX, pacmanY, null);
                goUp = false;
                goRight = false;
                goLeft = false;
                goDown = true;
                currentKeyCode = 4;
            } else {
                if (enterKeyCode == 1 && cantMove(pacmanX + pacmanSpeed, pacmanY)) {
                    if (currentKeyCode == 1) {
                        currentKeyCode = 0;
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 2) {
                        g.drawImage(pacmanLeft, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 3) {
                        g.drawImage(pacmanUp, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 4) {
                        g.drawImage(pacmanDown, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 0) {
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    }
                } else if (enterKeyCode == 2 && cantMove(pacmanX - pacmanSpeed, pacmanY)) {
                    if (currentKeyCode == 1) {
                        g.drawImage(pacmanRight, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 2) {
                        currentKeyCode = 0;
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 3) {
                        g.drawImage(pacmanUp, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 4) {
                        g.drawImage(pacmanDown, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 0) {
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    }
                } else if (enterKeyCode == 3 && cantMove(pacmanX, pacmanY - pacmanSpeed)) {
                    if (currentKeyCode == 1) {
                        g.drawImage(pacmanRight, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 2) {
                        g.drawImage(pacmanLeft, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 3) {
                        currentKeyCode = 0;
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 4) {
                        g.drawImage(pacmanDown, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 0) {
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    }
                } else if (enterKeyCode == 4 && cantMove(pacmanX, pacmanY + pacmanSpeed)) {
                    if (currentKeyCode == 1) {
                        g.drawImage(pacmanRight, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 2) {
                        g.drawImage(pacmanLeft, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 3) {
                        g.drawImage(pacmanUp, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 4) {
                        currentKeyCode = 0;
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    } else if (currentKeyCode == 0) {
                        g.drawImage(pacman, pacmanX, pacmanY, null);
                    }
                } else {
                    g.drawImage(pacman, pacmanX, pacmanY, null);
                }
            }
        }
    }
}
