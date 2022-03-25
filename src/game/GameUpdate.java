package game;

import object.map.MapObject;

public class GameUpdate {
    public void update() {
        if (GameJpanel.map.itg.isIntro == false) {
            GameJpanel.map.update();
            GameJpanel.pacman.update();
            GameJpanel.ghost.update();
            GameJpanel.change.update();
        }
        if (GameJpanel.map.passLevel == true && GameJpanel.level == 0) {
            GameJpanel.pathLogicMap = "resources/maplogic/map2.txt";
            GameJpanel.pathImageMap = "resources/images/map/map2.png";
            GameJpanel.map = new MapObject(GameJpanel.pathLogicMap, GameJpanel.pathImageMap);
            GameJpanel.level++;
        } else if (/*
                    * (PacmanJpanel.map.live == -1 || (PacmanJpanel.map.level == 1 &&
                    * PacmanJpanel.max == 1))
                    * &&
                    */(GameJpanel.map.eg.isOverGame == false)) {
            GameJpanel.pathLogicMap = "resources/maplogic/map1.txt";
            GameJpanel.pathImageMap = "resources/images/map/map1.png";
            GameJpanel.map = new MapObject(GameJpanel.pathLogicMap, GameJpanel.pathImageMap);
            GameJpanel.map.live = 3;
            GameJpanel.map.score = 0;
            GameJpanel.map.countScore = 191;// =191 de chet roi van chuyen man choi duoc
            GameJpanel.level = 0;
            GameJpanel.map.itg.isIntro = true;
            GameJpanel.map.itg.Play();
            GameJpanel.ghost.audioGhostMove.Stop();
            GameJpanel.running = false;
            GameJpanel.change.setPosition();
        }
    }
}
