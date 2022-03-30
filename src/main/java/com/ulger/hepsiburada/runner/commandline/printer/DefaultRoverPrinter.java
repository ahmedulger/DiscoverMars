package com.ulger.hepsiburada.runner.commandline.printer;

import com.ulger.hepsiburada.app.api.Rover;

public class DefaultRoverPrinter implements RoverPrinter {

    private static final String PRINTING_FORMAT = "%d %d %s\n";

    @Override
    public void print(Rover rover) {
        System.out.format(
                PRINTING_FORMAT,
                rover.getLocation().getX(),
                rover.getLocation().getY(),
                rover.getPosition().getKey());
    }
}