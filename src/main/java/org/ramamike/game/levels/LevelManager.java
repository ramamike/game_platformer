package org.ramamike.game.levels;

import org.ramamike.game.main.Game;
import org.ramamike.game.utils.LoadSafe;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.ramamike.game.main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level level_0;

    public LevelManager(Game game) {
        this.game = game;
//        levelSprite = LoadSafe.getSpriteAtlas(LoadSafe.LEVEL_ATLAS);
        importOutsideSprites();
        level_0 = new Level(LoadSafe.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage image = LoadSafe.getSpriteAtlas(LoadSafe.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                int index = i * 12 + j;
                levelSprite[index] = image.getSubimage(j*32, i*32,32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                int index = level_0.getSpriteIndex(j,i);
                g.drawImage(levelSprite[index], TILES_SIZE*j, TILES_SIZE*i, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }
}
