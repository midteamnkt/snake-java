package com.gamesnake.model;

import java.awt.*;

public class Point {
    private int x, y;
    private Color color;

    public Point(int x, int y, Color color) {
        this.setXY(x, y);
        this.color = color;
    }

    public void paint(Graphics g, int pointRadius) {
        g.setColor(color);
        g.fillOval(x * pointRadius, y * pointRadius, pointRadius, pointRadius);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}