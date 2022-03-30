package com.ulger.hepsiburada.runner.direction;

import com.ulger.hepsiburada.runner.DirectionType;

import java.util.HashMap;
import java.util.Map;

public class DirectionFactory {

    private final Map<DirectionType, Direction> directionsByType;

    public DirectionFactory() {
        directionsByType = new HashMap<>(3);
        directionsByType.put(DirectionType.L, new DirectionTurnLeft());
        directionsByType.put(DirectionType.R, new DirectionTurnRight());
        directionsByType.put(DirectionType.M, new DirectionMoveForward());
    }

    public Direction build(DirectionType directionType) {
        return directionsByType.get(directionType);
    }
}