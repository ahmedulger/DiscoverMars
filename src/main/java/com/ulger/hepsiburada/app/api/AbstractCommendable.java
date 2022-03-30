package com.ulger.hepsiburada.app.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCommendable implements Commendable {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCommendable.class);

    private static final int GRID_POINT = 1;

    private static final int TURNING_RIGHT_DEGREE = 90;
    private static final int TURNING_LEFT_DEGREE = -90;

    @Override
    public Position turnRight() {
        int newDegree = getPosition().getDegree() + TURNING_RIGHT_DEGREE;

        return Position
                .getMatchingPositionByDegree(newDegree)
                .orElseThrow(() -> new IllegalArgumentException("Position not found with degree: " + newDegree));
    }

    @Override
    public Position turnLeft() {
        int newDegree = getPosition().getDegree() + TURNING_LEFT_DEGREE;

        return Position
                .getMatchingPositionByDegree(newDegree)
                .orElseThrow(() -> new IllegalArgumentException("Position not found with degree: " + newDegree));
    }

    @Override
    public Location moveForward() {

        int newLocationX = getLocation().getX();
        int newLocationY = getLocation().getY();

        switch (getPosition()) {
            case N: newLocationY += GRID_POINT; break;
            case E: newLocationX += GRID_POINT; break;
            case S: newLocationY -= GRID_POINT; break;
            case W: newLocationX -= GRID_POINT; break;
        }

        checkNewLocationsIfProperToPlace(newLocationX, newLocationY);

        return new DefaultLocation(newLocationX, newLocationY);
    }

    protected abstract Location getLocation();

    protected abstract Position getPosition();

    protected abstract Location getStartingBoundaries();

    protected abstract Location getEndingBoundaries();

    private void checkNewLocationsIfProperToPlace(int newLocationX, int newLocationY) {

        boolean boundaryViolated = false;

        if (newLocationX > getEndingBoundaries().getX()) {
            boundaryViolated = true;
            logger.error("Plateau X axis boundaries violated. MaxPointX: {}, aimedPointX: {}", getEndingBoundaries().getX(), newLocationX);

        } else if (newLocationY > getEndingBoundaries().getY()) {
            boundaryViolated = true;
            logger.error("Plateau Y axis boundaries violated. MaxPointY: {}, aimedPointY: {}", getEndingBoundaries().getY(), newLocationY);

        } else if (newLocationX < getStartingBoundaries().getX()) {
            boundaryViolated = true;
            logger.error("Plateau X axis boundaries violated. MinPointX: {}, aimedPointX: {}", getStartingBoundaries().getX(), newLocationX);

        } else if (newLocationY < getStartingBoundaries().getY()) {
            boundaryViolated = true;
            logger.error("Plateau Y axis boundaries violated. MinPointY: {}, aimedPointY: {}", getStartingBoundaries().getY(), newLocationY);
        }

        if (boundaryViolated) {
            throw new IllegalArgumentException("Plateau boundaries violated. " +
                    "plateau starting location : " + getStartingBoundaries() + ", " +
                    "plateau ending location: " + getEndingBoundaries() + ", " +
                    "targetLocationX: " + newLocationX + ", " +
                    "targetLocationY: " + newLocationY);
        }
    }
}