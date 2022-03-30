package com.ulger.hepsiburada.app.api;

public interface Commendable {

    /**
     * Updates current position with +90 degrees
     */
    Position turnRight();

    /**
     * Updates current position with -90 degrees
     */
    Position turnLeft();

    /**
     * Moves a grid point forward direction
     */
    Location moveForward();
}