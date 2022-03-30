package com.ulger.hepsiburada.runner.direction;

import com.ulger.hepsiburada.app.api.Rover;

public class DirectionTurnRight implements Direction {

    @Override
    public void apply(Rover rover) {
        rover.turnRight();
    }
}