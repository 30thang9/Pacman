package object.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;

import audiogame.AudioGame;
import object.controlgame.EndGame;
import object.controlgame.IntroGame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class MapObject {
    public int map[][];
    public int rowsMap, colsMap;
    public Brick[][] brick;
    public int WIDTH_BRICK = 24;
    public int HEIGHT_BRICK = 24;
    private Image imageMap;
    public static int score = 0;
    public static int countScore = 0;
    public boolean passLevel = false;
    public static int live = 3;
    public boolean isWrite = false;
    private Image pacmanLive;
    public EndGame eg;
    public AudioGame audiogame = new AudioGame();
    public static IntroGame itg = new IntroGame();

    public MapObject(String pathLogicMap, String pathImageMap) {
        imageMap = new ImageIcon(pathImageMap).getImage();
        pacmanLive = new ImageIcon("resources/images/pacman/pacmanLive.png").getImage();
        eg = new EndGame();
        try {
            File myObj = new File(pathLogicMap);
            Scanner myReader = new Scanner(myObj);
            rowsMap = myReader.nextInt();
            colsMap = myReader.nextInt();
            map = new int[rowsMap][colsMap];
            brick = new Brick[rowsMap][colsMap];
            myReader.nextLine();
            while (myReader.hasNext()) {
                int x = 0;
                int y = 0;
                for (int i = 0; i < rowsMap; i++) {
                    for (int j = 0; j < colsMap; j++) {
                        int w = Integer.parseInt(myReader.next());
                        map[i][j] = w;
                        brick[i][j] = new Brick(x, y, map[i][j]);
                        if (map[i][j] == 1) {
                            countScore++;
                        }
                        x += WIDTH_BRICK;
                    }
                    y += HEIGHT_BRICK;
                    x = 0;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imageMap, 0, 0, null);
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[0].length; j++) {
                if (brick[i][j].getpositionLogic() == 1) {
                    if (!brick[i][j].isDestroyed()) {
                        brick[i][j].draw(g);
                    }
                }
            }
        }
        if (live == 1) {
            g.drawImage(pacmanLive, WIDTH_BRICK * 6, rowsMap * HEIGHT_BRICK + 10, null);
        } else if (live == 2) {
            g.drawImage(pacmanLive, WIDTH_BRICK * 7 + 10, rowsMap * HEIGHT_BRICK + 10, null);
            g.drawImage(pacmanLive, WIDTH_BRICK * 6 + 10, rowsMap * HEIGHT_BRICK + 10, null);
        } else if (live == 3) {
            g.drawImage(pacmanLive, WIDTH_BRICK * 8, rowsMap * HEIGHT_BRICK + 10, null);
            g.drawImage(pacmanLive, WIDTH_BRICK * 7, rowsMap * HEIGHT_BRICK + 10, null);
            g.drawImage(pacmanLive, WIDTH_BRICK * 6, rowsMap * HEIGHT_BRICK + 10, null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, WIDTH_BRICK, rowsMap * HEIGHT_BRICK + rowsMap + 6);
        /*
         * g.drawString("countScore: " + countScore, WIDTH_BRICK, rowsMap * HEIGHT_BRICK
         * + rowsMap * 2);
         */

    }

    public void update() {
    }
}
