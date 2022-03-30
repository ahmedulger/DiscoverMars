package com.ulger.hepsiburada.runner.commandline;

import com.ulger.hepsiburada.app.api.Rover;
import com.ulger.hepsiburada.runner.DirectionType;
import com.ulger.hepsiburada.runner.RoverDirector;
import com.ulger.hepsiburada.runner.RoverWithDirections;
import com.ulger.hepsiburada.runner.commandline.parser.CommandLineParser;
import com.ulger.hepsiburada.runner.commandline.parser.ParsingContext;
import com.ulger.hepsiburada.runner.commandline.printer.RoverPrinter;

import java.util.Collection;

public class CommandLineRunner {

    private final RoverPrinter roverPrinter;
    private final RoverDirector roverDirector;

    public CommandLineRunner(RoverPrinter roverPrinter, RoverDirector roverDirector) {
        this.roverPrinter = roverPrinter;
        this.roverDirector = roverDirector;
    }
    public void run(String[] args) {
        ParsingContext parsingContext = parseArgs(args);

        for (RoverWithDirections roverWithDirections : parsingContext.getRoversWithDirections()) {
            Rover rover = roverWithDirections.getRover();
            Collection<DirectionType> directionTypes = roverWithDirections.getDirectionTypes();

            roverDirector.run(rover, directionTypes);
            roverPrinter.print(rover);
        }
    }

    private ParsingContext parseArgs(String[] args) {
        return CommandLineParser.of(args).parse();
    }
}