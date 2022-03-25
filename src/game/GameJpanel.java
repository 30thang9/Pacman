package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.Runnable;
import javax.swing.JPanel;

import object.pacman.Pacman;
import playerprofile.WriteProfileMySQL;
import playerprofile.WriteProfileTXT;
import object.ghost.GhostMerge;
import object.map.Change;
import object.map.MapObject;

public class GameJpanel extends JPanel implements Runnable, KeyListener {
    public static boolean running = false;
    public int WIDTH_SCREEN;
    public int HEIGHT_SCREEN;
    public static String pathImageMap = "resources/images/map/map1.png";
    public static String pathLogicMap = "resources/maplogic/map1.txt";
    public static MapObject map;
    public static Pacman pacman;
    public static GhostMerge ghost;
    public static GameUpdate mapUpdate;
    public static Change change;
    public static Thread thread;
    public static WriteProfileTXT wpt;
    public static WriteProfileMySQL wpSQL;
    public static int level = 0;

    public GameJpanel() {
        init();
    }

    // tao
    public void init() {
        initObjects();
        WIDTH_SCREEN = map.colsMap * map.WIDTH_BRICK;
        HEIGHT_SCREEN = map.rowsMap * map.HEIGHT_BRICK;
        // dat kich thuoc cho jp
        this.setPreferredSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN + map.HEIGHT_BRICK * 2));
        this.setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    // tao doi tuong
    public void initObjects() {
        map = new MapObject(pathLogicMap, pathImageMap);
        pacman = new Pacman();
        ghost = new GhostMerge();
        mapUpdate = new GameUpdate();
        change = new Change();
        wpt = new WriteProfileTXT();
        wpSQL = new WriteProfileMySQL();
    }

    public void startGame() {
        thread = new Thread(this); // JP
        thread.start();
    }

    public void stopGame() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runningGame() {
        running = true;
    }

    public void update() {
        if (running) {
            mapUpdate.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObject(g);
        g.dispose();
    }

    public void drawObject(Graphics g) {
        if (map.itg.isIntro) {
            map.itg.draw(g);
            running = false;
        } else if (map.live == -1 || (map.passLevel == true && GameJpanel.level == 1)) {
            map.eg.draw(g);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            if (map.eg.isOverGame) {
                g.drawString("" + map.score, (map.colsMap - 10) * map.WIDTH_BRICK,
                        (map.rowsMap - 14) * map.HEIGHT_BRICK + map.rowsMap);
            }
            running = false;
            ghost.audioGhostMove.Stop();
        } else {
            map.draw(g);
            ghost.draw(g);
            pacman.draw(g);
        }
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        // System.out.println(lastTime);
        double FPS = 60.0;
        double delta = 0;
        double drawInterval = 1000000000 / FPS;// khoang thoi gian ve
        while (thread != null) {
            long currentTime = System.nanoTime();
            // System.out.println(currentTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                pacman.enterKeyCode = 1;
                if (!running)
                    running = true;
                break;
            case KeyEvent.VK_LEFT:
                pacman.enterKeyCode = 2;
                if (!running)
                    running = true;
                break;
            case KeyEvent.VK_UP:
                pacman.enterKeyCode = 3;
                if (!running)
                    running = true;
                break;
            case KeyEvent.VK_DOWN:
                pacman.enterKeyCode = 4;
                if (!running)
                    running = true;
                break;
            case KeyEvent.VK_P:
                if (running)
                    running = false;
                ghost.audioGhostMove.Stop();
                break;
            case KeyEvent.VK_SPACE:
                map.itg.isIntro = false;
                map.itg.Stop();
                break;
            case KeyEvent.VK_ENTER:
                map.eg.isOverGame = false;
                if (map.eg.isOverGame == false) {
                    running = true;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (map.eg.isOverGame) {
                    System.exit(0);
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
