package object.controlgame;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class EndGame {
    private Image endImage;
    public boolean isOverGame;

    public EndGame() {
        loadImage();
        isOverGame = true;
    }

    public void loadImage() {
        endImage = new ImageIcon("resources/images/map/gameOver.png").getImage();
    }

    public void draw(Graphics g) {
        if (isOverGame) {
            g.drawImage(endImage, 0, 0, null);
        }
    }
}
