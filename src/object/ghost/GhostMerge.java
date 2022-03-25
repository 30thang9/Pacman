package object.ghost;

import java.awt.Graphics;

import audiogame.AudioLoop;
import game.GameJpanel;

public class GhostMerge {
    public GhostGreen ghostGreen;
    public GhostRed ghostRed;
    public GhostYellow ghostYellow;
    public GhostPink ghostPink;
    public static AudioLoop audioGhostMove;

    public GhostMerge() {
        audioGhostMove = new AudioLoop("resources/sounds/ghost/siren_2.wav");
        Thread t = new Thread(audioGhostMove);
        t.setPriority(Thread.MIN_PRIORITY);
        if (GameJpanel.running) {
            t.start();
        }
        ghostGreen = new GhostGreen();
        ghostRed = new GhostRed();
        ghostYellow = new GhostYellow();
        ghostPink = new GhostPink();
    }

    public void update() {
        ghostGreen.update();
        ghostRed.update();
        ghostYellow.update();
        ghostPink.update();
    }

    public void draw(Graphics g) {
        ghostGreen.draw(g);
        ghostRed.draw(g);
        ghostYellow.draw(g);
        ghostPink.draw(g);
    }

    public void setPositionXY() {
        ghostGreen.setGhostGreenXY();
        ghostRed.setGhostRedXY();
        ghostYellow.setGhostYellowXY();
        ghostPink.setGhostPinkXY();
    }

    public void setDir() {
        ghostGreen.dir = 0;
        ghostRed.dir = 0;
        ghostYellow.dir = 0;
        ghostPink.dir = 0;
    }
}
