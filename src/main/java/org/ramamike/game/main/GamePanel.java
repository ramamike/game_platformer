package org.ramamike.game.main;

import org.ramamike.game.inputs.KeyBoardInputs;
import org.ramamike.game.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static org.ramamike.game.main.Game.GAME_HEIGHT;
import static org.ramamike.game.main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }


    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }

}
