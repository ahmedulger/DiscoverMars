package com.ulger.hepsiburada.runner.direction;

import com.ulger.hepsiburada.app.api.Rover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectionTurnRight implements Direction {

    private static final Logger logger = LoggerFactory.getLogger(DirectionTurnRight.class);

    @Override
    public void apply(Rover rover) {
        logger.info("Direction turnRight applying on rover with id: {}", rover.getId());
        rover.turnRight();
    }
}