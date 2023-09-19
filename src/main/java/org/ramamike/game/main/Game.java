package org.ramamike.game.main;

import org.ramamike.game.entities.Player;
import org.ramamike.game.levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;
    private LevelManager levelManager;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;

    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE*TILES_IN_HEIGHT;


    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200, 200);
        levelManager = new LevelManager(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        levelManager.update();
        player.update();
    }

    public void render(Graphics g) {
        player.render(g);
        levelManager.draw(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaUpdate = 0;
        double deltaFrames = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaUpdate += (currentTime - previousTime) / timePerUpdate;
            deltaFrames += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaUpdate >= 1) {
                update();
                ++updates;
                --deltaUpdate;
            }

            if (deltaFrames >= 1) {
                gamePanel.repaint();
                frames++;
                deltaFrames--;
            }

            if ((System.currentTimeMillis() - lastCheck) >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPD: " + updates);
                frames = 0;
                updates = 0;
            }

        }
    }

    public Player getPlayer() {
        return player;
    }


    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
