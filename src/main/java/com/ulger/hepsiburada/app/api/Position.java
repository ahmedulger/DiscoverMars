package com.ulger.hepsiburada.app.api;

import java.util.Arrays;
import java.util.Optional;

public enum Position {

    N("N", 0),
    E("E", 90),
    S("S", 180),
    W("W", 270);

    private final String key;
    private final int degree;

    Position(String key, int degree) {
        this.key = key;
        this.degree = degree;
    }

    public String getKey() {
        return key;
    }

    public int getDegree() {
        return degree;
    }

    public static Optional<Position> getMatchingPositionByDegree(int targetDegree) {

        int pureDegree = targetDegree % 360;
        int finalDegree = pureDegree < 0 ? pureDegree + 360 : pureDegree;

        return Arrays
                .stream(Position.values())
                .filter(position -> finalDegree == position.degree)
                .findFirst();
    }

    public static Optional<Position> getMatchingPositionByKey(String key) {
        return Arrays
                .stream(Position.values())
                .filter(position -> position.key.equalsIgnoreCase(key))
                .findFirst();
    }
}