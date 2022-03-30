package com.ulger.hepsiburada.app.api;

public class DefaultLocation implements Location {

    private final int x;
    private final int y;

    public DefaultLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "DefaultLocation{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}