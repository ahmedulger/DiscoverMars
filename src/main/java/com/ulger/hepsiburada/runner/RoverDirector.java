package com.ulger.hepsiburada.runner;

import com.ulger.hepsiburada.app.api.Rover;

import java.util.Collection;

public interface RoverDirector {

    void run(Rover rover, Collection<DirectionType> directionTypes);
}