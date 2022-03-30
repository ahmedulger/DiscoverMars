package com.ulger.hepsiburada.app.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultPlateau implements Plateau {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPlateau.class);

    private final Location startingLocation;
    private final Location endingLocation;
    private Set<Rover> rovers;

    public DefaultPlateau(Location startingLocation, Location endingLocation) {
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.rovers = new LinkedHashSet<>();
    }

    public DefaultPlateau(Location startingLocation, Location endingLocation, Set<Rover> rovers) {
        this(startingLocation, endingLocation);
        this.rovers = rovers;

        assert rovers != null : "Rover list must be empty or not null";
    }

    @Override
    public Location getStartingLocation() {
        return startingLocation;
    }

    @Override
    public Location getEndingLocation() {
        return endingLocation;
    }

    @Override
    public Set<Rover> getRovers() {
        return new LinkedHashSet<>(rovers);
    }

    @Override
    public void addRover(Rover rover) {
        rovers.add(rover);

        logger.info("Added new rover to plateau. RoverId: {}, RoverLocation: {}, RoverPosition: {}",
                rover.getId(),
                rover.getLocation(),
                rover.getPosition());
    }
}