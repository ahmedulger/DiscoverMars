package com.ulger.hepsiburada.runner.commandline.parser;

import com.ulger.hepsiburada.app.api.Plateau;
import com.ulger.hepsiburada.runner.RoverWithDirections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParsingContext {

    private Plateau plateau;
    private List<RoverWithDirections> roversWithDirections;

    ParsingContext() {
        this.roversWithDirections = new LinkedList<>();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public List<RoverWithDirections> getRoversWithDirections() {
        return new ArrayList<>(roversWithDirections);
    }

    void addRoverWithDirections(RoverWithDirections roverWithDirections) {
        this.roversWithDirections.add(roverWithDirections);
    }
}