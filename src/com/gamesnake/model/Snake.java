package com.gamesnake.model;

import com.gamesnake.GameSnake;
import java.awt.*;
import java.util.ArrayList;

public class Snake {
    // Key codes for arrow keys
    public static final int LEFT = 37;
    public static final int UP = 38;
    public static final int RIGHT = 39;
    public static final int DOWN = 40;

    // Key codes for WASD
    public static final int A = 65;
    public static final int W = 87;
    public static final int D = 68;
    public static final int S = 83;

    private ArrayList<Point> body = new ArrayList<>();
    private int direction;
    private GameSnake game;

    public Snake(int x, int y, int length, int direction, GameSnake game) {
        this.game = game;
        for (int i = 0; i < length; i++) {
            Point point = new Point(x - i, y, Color.green);
            body.add(point);
        }
        this.direction = direction;
    }

    public boolean isInsideSnake(int x, int y) {
        for (Point point : body) {
            if ((point.getX() == x) && (point.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFood(Point food) {
        return ((body.get(0).getX() == food.getX()) && (body.get(0).getY() == food.getY()));
    }

    public void move() {
        int x = body.get(0).getX();
        int y = body.get(0).getY();

        if (direction == LEFT) x--;
        if (direction == RIGHT) x++;
        if (direction == UP) y--;
        if (direction == DOWN) y++;

        if (x > game.getFieldWidth() - 1) x = 0;
        if (x < 0) x = game.getFieldWidth() - 1;
        if (y > game.getFieldHeight() - 1) y = 0;
        if (y < 0) y = game.getFieldHeight() - 1;

        game.setGameOver(isInsideSnake(x, y));

        body.add(0, new Point(x, y, Color.green));
        if (isFood(game.getFood())) {
            game.getFood().eat();
            game.getFrame().setTitle(game.getTitle() + " | Размер змейки: " + body.size());
        } else {
            body.remove(body.size() - 1);
        }
    }

    public void setDirection(int direction) {
        // Convert WASD to equivalent arrow keys
        int newDirection = direction;
        if (direction == A) newDirection = LEFT;
        if (direction == W) newDirection = UP;
        if (direction == D) newDirection = RIGHT;
        if (direction == S) newDirection = DOWN;

        // Check if the key is valid (arrow or WASD)
        if ((newDirection >= LEFT && newDirection <= DOWN)) {
            // Prevent 180-degree turns
            if (Math.abs(this.direction - newDirection) != 2) {
                this.direction = newDirection;
            }
        }
    }

    public void paint(Graphics g) {
        for (Point point : body) {
            point.paint(g, game.getPointRadius());
        }
    }

    public ArrayList<Point> getBody() {
        return body;
    }
}