package com.gamesnake.view;

import com.gamesnake.GameSnake;
import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private GameSnake game;
    private final String GAME_OVER_MSG = "GAME OVER";
    private final String CONTROLS_MSG = "Управление: Стрелочки или WASD";

    public GameCanvas(GameSnake game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        game.getSnake().paint(g);
        game.getFood().paint(g);

        // Display controls hint
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(CONTROLS_MSG, 10, 15);

        if (game.isGameOver()) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            FontMetrics fm = g.getFontMetrics();
            int width = game.getFieldWidth() * game.getPointRadius() + game.FILLED_DX;
            int height = game.getFieldHeight() * game.getPointRadius() + game.FILLED_DY;
            g.drawString(GAME_OVER_MSG, (width - fm.stringWidth(GAME_OVER_MSG)) / 2, height / 2);
        }
    }
}