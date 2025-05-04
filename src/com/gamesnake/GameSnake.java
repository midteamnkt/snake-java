package com.gamesnake;

import com.gamesnake.model.Food;
import com.gamesnake.model.Snake;
import com.gamesnake.view.GameCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameSnake {
    // Game constants
    final String TITLE_OF_PROGRAM = "MID";
    final int POINT_RADIUS = 20;
    final int FILLED_WIDTH = 30;
    final int FILLED_HEIGHT = 20;
    public static final int FILLED_DX = 6;
    public static final int FILLED_DY = 28;
    final int START_LOCATION = 200;
    final int START_SNAKE_SIZE = 3;
    final int START_SNAKE_X = 10;
    final int START_SNAKE_Y = 10;
    final int SHOW_DEALAY = 150;
    final int START_DIRECTION = Snake.RIGHT;

    private Snake snake;
    private Food food;
    private JFrame frame;
    private GameCanvas canvasPanel;
    private boolean gameOver = false;

    public static void main(String[] args) {
        new GameSnake().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAM + " | Размер змейки: " + START_SNAKE_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FILLED_WIDTH * POINT_RADIUS + FILLED_DX, FILLED_HEIGHT * POINT_RADIUS + FILLED_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        canvasPanel = new GameCanvas(this);
        canvasPanel.setBackground(Color.white);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });
        frame.setVisible(true);

        snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, START_DIRECTION, this);
        food = new Food(this);

        while (!gameOver) {
            snake.move();
            if (food.isEaten()) {
                food.next();
            }
            canvasPanel.repaint();
            try {
                Thread.sleep(SHOW_DEALAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getPointRadius() {
        return POINT_RADIUS;
    }

    public int getFieldWidth() {
        return FILLED_WIDTH;
    }

    public int getFieldHeight() {
        return FILLED_HEIGHT;
    }

    public String getTitle() {
        return TITLE_OF_PROGRAM;
    }

    public JFrame getFrame() {
        return frame;
    }
}