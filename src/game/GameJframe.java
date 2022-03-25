package game;

import javax.swing.JFrame;

public class GameJframe extends JFrame {
    GameJframe() {
        GameJpanel jp = new GameJpanel();
        this.add(jp);
        this.setTitle("Pacman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        jp.startGame();
    }
}
