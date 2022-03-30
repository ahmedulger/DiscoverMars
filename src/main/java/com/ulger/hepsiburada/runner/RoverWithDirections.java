package com.ulger.hepsiburada.runner;

import com.ulger.hepsiburada.app.api.Rover;

import java.util.List;

public class RoverWithDirections {

    private Rover rover;
    private List<DirectionType> directionTypes;

    public RoverWithDirections(Rover rover, List<DirectionType> directionTypes) {
        this.rover = rover;
        this.directionTypes = directionTypes;
    }

    public Rover getRover() {
        return rover;
    }

    public List<DirectionType> getDirectionTypes() {
        return directionTypes;
    }
}