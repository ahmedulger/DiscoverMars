package com.ulger.hepsiburada;

import com.ulger.hepsiburada.runner.DefaultRoverDirector;
import com.ulger.hepsiburada.runner.RoverDirector;
import com.ulger.hepsiburada.runner.commandline.CommandLineRunner;
import com.ulger.hepsiburada.runner.commandline.printer.DefaultRoverPrinter;
import com.ulger.hepsiburada.runner.commandline.printer.RoverPrinter;
import com.ulger.hepsiburada.runner.direction.DirectionFactory;

public class Application {

    public static void main(String[] args) {
        RoverPrinter roverPrinter = new DefaultRoverPrinter();
        DirectionFactory directionFactory = new DirectionFactory();
        RoverDirector roverDirector = new DefaultRoverDirector(directionFactory);

        new CommandLineRunner(roverPrinter, roverDirector).run(args);
    }
}