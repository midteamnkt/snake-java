package com.gamesnake.model;

import com.gamesnake.GameSnake;
import java.awt.*;
import java.util.Random;

public class Food extends Point {
    private GameSnake game;
    private Random random = new Random();

    public Food(GameSnake game) {
        super(-1, -1, Color.red);
        this.game = game;
    }

    public void eat() {
        this.setXY(-1, -1);
    }

    public boolean isEaten() {
        return this.getX() == -1;
    }

    public void next() {
        int x, y;
        do {
            x = random.nextInt(game.getFieldWidth());
            y = random.nextInt(game.getFieldHeight());
        } while (game.getSnake().isInsideSnake(x, y));
        this.setXY(x, y);
    }

    // Добавленный метод
    public void paint(Graphics g) {
        if (!isEaten()) {
            super.paint(g, game.getPointRadius());
        }
    }
}