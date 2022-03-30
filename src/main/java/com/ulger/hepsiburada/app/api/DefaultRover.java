package com.ulger.hepsiburada.app.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class DefaultRover extends AbstractCommendable implements Rover {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRover.class);

    private final int id;
    private Location location;
    private Position position;
    private final Plateau plateau;

    public DefaultRover(int id, Location location, Position position, Plateau plateau) {
        assert location != null : "Location is required";
        assert position != null : "Position is required";
        assert plateau != null : "Plateau is required";

        this.id = id;
        this.location = location;
        this.position = position;
        this.plateau = plateau;

        placeThisRoverOnPlateau(plateau);
    }

    private void placeThisRoverOnPlateau(Plateau plateau) {
        plateau.addRover(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Position turnRight() {
        this.position = super.turnRight();
        logger.info("Rover with id: {} turned right and new position: {}", id, position);
        return position;
    }

    @Override
    public Position turnLeft() {
        this.position = super.turnLeft();
        logger.info("Rover with id: {} turned left and new position: {}", id, position);
        return position;
    }

    @Override
    public Location moveForward() {
        this.location = super.moveForward();
        logger.info("Rover with id: {} moved forward and new location: {}", id, location);
        return location;
    }

    @Override
    protected Location getStartingBoundaries() {
        return plateau.getStartingLocation();
    }

    @Override
    protected Location getEndingBoundaries() {
        return plateau.getEndingLocation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultRover that = (DefaultRover) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}