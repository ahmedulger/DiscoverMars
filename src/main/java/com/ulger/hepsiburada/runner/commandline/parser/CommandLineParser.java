package com.ulger.hepsiburada.runner.commandline.parser;

import com.ulger.hepsiburada.app.api.*;
import com.ulger.hepsiburada.runner.DirectionType;
import com.ulger.hepsiburada.runner.RoverWithDirections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineParser {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineParser.class);

    private final List<String> args;
    private final ParsingContext parsingContext;

    private CommandLineParser(String[] args) {
        this.args = Arrays.asList(args);
        this.parsingContext = new ParsingContext();
    }

    public static CommandLineParser of(String[] args) {
        return new CommandLineParser(args);
    }

    public ParsingContext parse() {
        this.parsePlateauEndingLocations();
        this.parseRoversWithDirections();
        return parsingContext;
    }

    private CommandLineParser parsePlateauEndingLocations() {
        logger.info("Parsing plateau information");

        String plateauEndLocationTextX = getSingleArg(0, ParsingContextField.PLATEAU_END_LOCATION_X);
        String plateauEndLocationTextY = getSingleArg(1, ParsingContextField.PLATEAU_END_LOCATION_Y);

        int plateauEndLocationX = parseNumber(plateauEndLocationTextX.trim(), ParsingContextField.PLATEAU_END_LOCATION_X);
        int plateauEndLocationY = parseNumber(plateauEndLocationTextY.trim(), ParsingContextField.PLATEAU_END_LOCATION_Y);

        Location plateStartingLocation = new DefaultLocation(0, 0);
        Location plateEndingLocation = new DefaultLocation(plateauEndLocationX, plateauEndLocationY);

        Plateau plateau = new DefaultPlateau(plateStartingLocation, plateEndingLocation);
        parsingContext.setPlateau(plateau);

        logger.info("Plateau information parsed. Plateau starting coordinates: {} and ending coordinates: {}",
                plateau.getStartingLocation(),
                plateau.getEndingLocation());

        return this;
    }

    private CommandLineParser parseRoversWithDirections() {

        int partitionCount = (args.size() - 2) / 4;
        if (partitionCount < 1) {
            throw new IllegalArgumentException("At least one rover is required");
        }

        int rowerId = 0;

        for (int partitionIndex = 0; partitionIndex < partitionCount; partitionIndex++) {
            int index = partitionIndex * 4 + 2;

            String rowerLocationX = getSingleArg(index, ParsingContextField.ROVER_LOCATION_X);
            String rowerLocationY = getSingleArg(index + 1, ParsingContextField.ROVER_LOCATION_Y);
            String rowerPosition = getSingleArg(index + 2, ParsingContextField.ROVER_POSITION);
            String rowerDirections = getSingleArg(index + 3, ParsingContextField.ROVER_DIRECTIONS);

            RoverWithDirections roverWithDirections = buildRoverWithDirections(rowerId++, rowerLocationX, rowerLocationY, rowerPosition, rowerDirections);
            parsingContext.addRoverWithDirections(roverWithDirections);
        }

        return this;
    }

    private int parseNumber(String text, String field) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            throw new IllegalArgumentException("Number format is invalid for field " + field + " with value: " + text);
        }
    }

    private String getSingleArg(int index, String argName) {
        if (index >= args.size()) {
            throw new IllegalArgumentException("Argument required. Missing argument on line Number: " + (index + 1) + ". Argument description: " + argName);
        }

        return args.get(index);
    }

    private RoverWithDirections buildRoverWithDirections(
            int rowerId,
            String rowerLocationX,
            String rowerLocationY,
            String rowerPosition,
            String rowerDirections) {

        logger.info("Parsing rover information with directions. RoverId: {}", rowerId);

        Location location = buildRowerLocation(rowerLocationX, rowerLocationY);
        Position position = buildRoverPosition(rowerPosition);
        List<DirectionType> directions = buildDirections(rowerDirections);

        Rover rover = new DefaultRover(rowerId, location, position, parsingContext.getPlateau());

        logger.info("Rover information with directions parsed. RoverId: {}", rowerId);

        return new RoverWithDirections(rover, directions);
    }

    private Location buildRowerLocation(String rowerLocationX, String rowerLocationY) {

        logger.info("Parsing rover location. Coordinates X: {} and Y: {}", rowerLocationX, rowerLocationY);

        if (rowerLocationX == null || rowerLocationX.equals("")) {
            throw new IllegalArgumentException("Rover location X is required");
        }

        if (rowerLocationY == null || rowerLocationY.equals("")) {
            throw new IllegalArgumentException("Rover location Y is required");
        }

        return new DefaultLocation(
                parseNumber(rowerLocationX, ParsingContextField.ROVER_LOCATION_X),
                parseNumber(rowerLocationY, ParsingContextField.ROVER_LOCATION_Y));
    }

    private Position buildRoverPosition(String rowerPosition) {
        logger.info("Parsing rover position. Position: {}", rowerPosition);

        if (rowerPosition == null || rowerPosition.equals("")) {
            throw new IllegalArgumentException("Rover position is required");
        }

        return Position
                .getMatchingPositionByKey(rowerPosition)
                .orElseThrow(() -> new IllegalArgumentException("Rover position not found with key: " + rowerPosition));
    }

    private List<DirectionType> buildDirections(String rowerDirectionsText) {
        logger.info("Parsing rover directions. rowerDirectionsText: {}", rowerDirectionsText);

        if (rowerDirectionsText == null || rowerDirectionsText.equals("")) {
            throw new IllegalArgumentException("Rover direction list is required");
        }

        String[] directions = rowerDirectionsText
                .trim()
                .split("");

        return Arrays
                .stream(directions)
                .map(directionKey -> DirectionType
                        .getMatchingDirectionByKey(directionKey)
                        .orElseThrow(() -> new IllegalArgumentException("Direction not found with key: " + directionKey)))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}