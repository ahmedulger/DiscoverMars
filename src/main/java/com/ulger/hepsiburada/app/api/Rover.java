package com.ulger.hepsiburada.app.api;

/**
 * The vehicle that covers plateau
 */
public interface Rover extends Commendable {

    int getId();

    /**
     * Current location of vehicle.
     * @return location information keeps X and Y coordinates
     */
    Location getLocation();

    /**
     * Current position of vehicle.
     * @return the direction
     */
    Position getPosition();
}