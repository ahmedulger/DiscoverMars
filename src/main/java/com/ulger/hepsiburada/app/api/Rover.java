package com.ulger.hepsiburada.app.api;

public interface Rover extends Commendable {

    int getId();

    Location getLocation();

    Position getPosition();
}