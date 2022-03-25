package audiogame;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.GameJpanel;

public class AudioLoop implements Runnable {
    private Clip clip;

    public AudioLoop(String path) {
        try {
            File f = new File(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void PlayLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void Play() {
        clip.start();
    }

    public void Reset() {
        clip.setMicrosecondPosition(0);
    }

    public void Stop() {
        clip.stop();
    }

    public void Close() {
        clip.close();
    }

    @Override
    public void run() {
        this.PlayLoop();
    }

}
