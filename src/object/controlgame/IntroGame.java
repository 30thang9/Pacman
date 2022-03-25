package object.controlgame;

import java.awt.Image;
import javax.swing.ImageIcon;

import audiogame.AudioLoop;

import java.awt.Graphics;

public class IntroGame {
    private Image introImage;
    public boolean isIntro;
    public static AudioLoop introMusic;

    public IntroGame() {
        loadImage();
        introMusic = new AudioLoop("resources/sounds/game/game_beginning.wav");
        Thread t = new Thread(introMusic);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
        isIntro = true;
    }

    public void loadImage() {
        introImage = new ImageIcon("resources/images/map/introScreen.jpg").getImage();
    }

    public void draw(Graphics g) {
        if (isIntro) {
            g.drawImage(introImage, 0, 0, null);
        }
    }

    public void Stop() {
        introMusic.Stop();
    }

    public void Play() {
        introMusic.PlayLoop();
        introMusic.Reset();
    }

}
