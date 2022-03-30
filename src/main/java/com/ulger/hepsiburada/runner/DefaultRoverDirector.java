package com.ulger.hepsiburada.runner;

import com.ulger.hepsiburada.app.api.Rover;
import com.ulger.hepsiburada.runner.direction.DirectionFactory;

import java.util.Collection;

public class DefaultRoverDirector implements RoverDirector {

    private final DirectionFactory directionFactory;

    public DefaultRoverDirector(DirectionFactory directionFactory) {
        this.directionFactory = directionFactory;
    }

    @Override
    public void run(Rover rover, Collection<DirectionType> directionTypes) {
        directionTypes
                .stream()
                .map(directionFactory::build)
                .forEach(direction -> direction.apply(rover));
    }
}