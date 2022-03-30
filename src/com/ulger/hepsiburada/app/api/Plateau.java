package com.ulger.hepsiburada.app.api;

import java.util.Set;

/**
 * This interfaces refers a map that has starting and ending locations.
 */
public interface Plateau {

    /**
     * Keeps the starting coordinates of plateau
     * @return coordinates
     */
    Location getStartingLocation();

    /**
     * Keeps the ending coordinates of plateau
     * @return coordinates
     */
    Location getEndingLocation();

    /**
     * Get rovers places on this plateau.
     * Must return immutable set of rovers.
     * @return set of rovers
     */
    Set<Rover> getRovers();

    /**
     * Places a rover on this plateau
     * @param rover tobe placed rover
     */
    void addRover(Rover rover);
}