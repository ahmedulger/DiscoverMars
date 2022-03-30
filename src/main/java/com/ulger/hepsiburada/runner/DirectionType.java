package com.ulger.hepsiburada.runner;

import java.util.Arrays;
import java.util.Optional;

public enum DirectionType {

    L("L"),  // Turn left
    R("R"),  // Turn right
    M("M");  // Move forward

    private String key;

    DirectionType(String key) {
        this.key = key;
    }

    public static Optional<DirectionType> getMatchingDirectionByKey(String key) {
        return Arrays
                .stream(DirectionType.values())
                .filter(direction -> direction.key.equalsIgnoreCase(key))
                .findFirst();
    }
}