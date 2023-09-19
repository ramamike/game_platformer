package org.ramamike.game.levels;

import org.ramamike.game.main.Game;
import org.ramamike.game.utils.LoadSafe;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage levelSprite;

    public LevelManager(Game game) {
        this.game = game;
        levelSprite = LoadSafe.getSpriteAtlas(LoadSafe.LEVEL_ATLAS);
    }

    public void draw(Graphics g){
        g.drawImage(levelSprite, 0,0, null);
    }

    public void update(){

    }
}
