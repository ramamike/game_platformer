package org.ramamike.game.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.ramamike.game.utils.Constants.PlayerConstants.*;

public class Player extends Entity {

    private BufferedImage[][] animation;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private boolean moving = false;

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    private boolean attacking = false;

    public Player(float x, float y) {
        super(x, y);
        loadAnimation();
    }


    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {

        g.drawImage(animation[playerAction][animationIndex], (int) x, (int) y, 128, 80, null);
    }

    private void loadAnimation() {
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");
        try {
            BufferedImage image = ImageIO.read(inputStream);
            animation = new BufferedImage[9][6];

            for (int j = 0; j < animation.length; j++) {
                for (int i = 0; i < animation[j].length; i++) {
                    animation[j][i] = image.getSubimage(i * 64, j * 40, 64, 40);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {

        int startAnimation = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if(attacking) {
            playerAction = ATTACK_1;
        }
        if(startAnimation!=playerAction) {
            resetAnimTick();
        }
    }

    private void resetAnimTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePosition() {

        moving = false;
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }


    public void setLeft(boolean left) {
        this.left = left;
    }


    public void setUp(boolean up) {
        this.up = up;
    }


    public void setRight(boolean right) {
        this.right = right;
    }


    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
