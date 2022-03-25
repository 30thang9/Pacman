package object.map;

import game.GameJpanel;
import object.ghost.GhostMerge;
import object.pacman.Pacman;
import playerprofile.WriteProfileMySQL;
import playerprofile.WriteProfileTXT;

public class Change {
    public void update() {
        eatFood();
        passLevel();
        eatGhost();
        WriteScore();
    }

    public void eatFood() {
        MapObject map = GameJpanel.map;
        GhostMerge ghost = GameJpanel.ghost;
        Pacman pacman = GameJpanel.pacman;
        // check eat food
        for (int i = 0; i < map.brick.length; i++) {
            for (int j = 0; j < map.brick[0].length; j++) {
                if (map.brick[i][j].getpositionLogic() == 1) {
                    if (pacman.getRect_Pacman().intersects(map.brick[i][j].getRectFood(8, 8))) {
                        if (!map.brick[i][j].isDestroyed()) {
                            map.score++;
                            map.brick[i][j].setDestroyed(true);
                            map.audiogame.start(0);
                        }
                    }
                }
            }
        }
    }

    public void passLevel() {
        MapObject map = GameJpanel.map;
        GhostMerge ghost = GameJpanel.ghost;
        // check chuyen man
        if (map.score == map.countScore) {
            map.passLevel = true;
            ghost.audioGhostMove.Stop();
            try {
                if (GameJpanel.level < 1) {
                    GameJpanel.thread.sleep(2000);
                } else {
                    GameJpanel.thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            GameJpanel.running = false;
            setPosition();
            //
            ghost.audioGhostMove.PlayLoop();
        }
    }

    public void eatGhost() {
        MapObject map = GameJpanel.map;
        GhostMerge ghost = GameJpanel.ghost;
        Pacman pacman = GameJpanel.pacman;
        // check eat Ghost
        if (checkEatGhost()) {
            map.live--; // mang giam di
            ghost.audioGhostMove.Stop();
            if (map.live != -1) {
                map.audiogame.start(1);
            } else {
                map.audiogame.start(2);
            }
            try {
                GameJpanel.thread.sleep(1500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            GameJpanel.running = false;
            setPosition();
            //
            ghost.audioGhostMove.PlayLoop();
        }
        if (!GameJpanel.running) {
            ghost.audioGhostMove.Stop();
        } else {
            ghost.audioGhostMove.PlayLoop();
        }
    }

    public boolean checkEatGhost() {
        GhostMerge ghost = GameJpanel.ghost;
        Pacman pacman = GameJpanel.pacman;
        return pacman.getRect_Pacman().intersects(ghost.ghostRed.getRectGhostRed())
                || pacman.getRect_Pacman().intersects(ghost.ghostGreen.getRectGhostGreen())
                || pacman.getRect_Pacman().intersects(ghost.ghostPink.getRectGhostPink())
                || pacman.getRect_Pacman().intersects(ghost.ghostYellow.getRectGhostYellow());
    }

    public void setPosition() {
        GhostMerge ghost = GameJpanel.ghost;
        Pacman pacman = GameJpanel.pacman;
        pacman.setPacmanXY();
        pacman.enterKeyCode = 0;
        // set position Ghost
        ghost.setPositionXY();
        ghost.setDir();
    }

    public void WriteScore() {
        MapObject map = GameJpanel.map;
        WriteProfileTXT wpt = GameJpanel.wpt;
        WriteProfileMySQL wpSQL = GameJpanel.wpSQL;
        if (GameJpanel.map.live == -1 || (GameJpanel.map.passLevel == true && GameJpanel.level == 1)) {
            if (map.eg.isOverGame) {
                wpt.Write();
                wpSQL.insert();
            }

        }
    }
}
