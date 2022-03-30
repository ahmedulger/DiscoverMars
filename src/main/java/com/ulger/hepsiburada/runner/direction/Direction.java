package com.ulger.hepsiburada.runner.direction;

import com.ulger.hepsiburada.app.api.Rover;

public interface Direction {

    void apply(Rover rover);
}