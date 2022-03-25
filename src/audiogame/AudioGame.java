package audiogame;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.GameJpanel;

public class AudioGame {
    private Clip clip;
    private File pathAudio[] = new File[30];
    public static boolean playAudio = true;

    public AudioGame() {
        loadAudio();
    }

    public void loadAudio() {
        pathAudio[0] = new File("resources/sounds/pacman/waka.wav");
        pathAudio[1] = new File("resources/sounds/pacman/eat_ghost.wav");
        pathAudio[2] = new File("resources/sounds/pacman/gameOver.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(pathAudio[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void start(int i) {
        setFile(i);
        clip.start();
    }

    public void startLoop(int i) {
        setFile(i);
        // clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void close() {
        clip.close();
    }

}