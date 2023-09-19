package org.ramamike.game.utils;

import org.ramamike.game.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSafe {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage image = null;
        InputStream inputStream = LoadSafe.class.getResourceAsStream('/' + fileName);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    public static int[][] getLevelData(){
        int[][] lvlDate = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j,i));
                int value = color.getRed();
                if (value>=48) {
                    value = 0;
                }
                lvlDate[i][j] = value;
            }
        }
        return lvlDate;
    }

}
