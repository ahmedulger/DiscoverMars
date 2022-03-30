package com.ulger.hepsiburada.app.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefaultRoverTest {

    private Location plateauStartingLocation = new DefaultLocation(0, 0);
    private Location plateauEndingLocation = new DefaultLocation(3, 3);
    private Plateau plateau = new DefaultPlateau(plateauStartingLocation, plateauEndingLocation);

    private Location roverLocation = new DefaultLocation(0, 0);
    private Position roverPosition = Position.N;
    private Rover rover = new DefaultRover(0, roverLocation, roverPosition, plateau);

    @Test
    public void should_return_location_and_position_when_turned_and_moved_successfully() {

        // given
        // when
        rover.turnRight();
        rover.moveForward();

        // then
        Assertions.assertThat(rover.getPosition()).isEqualTo(Position.E);
        Assertions.assertThat(rover.getLocation().getX()).isEqualTo(1);
        Assertions.assertThat(rover.getLocation().getY()).isEqualTo(0);

        // given
        // when
        rover.turnLeft();
        rover.moveForward();
        rover.moveForward();

        // then
        Assertions.assertThat(rover.getPosition()).isEqualTo(Position.N);
        Assertions.assertThat(rover.getLocation().getX()).isEqualTo(1);
        Assertions.assertThat(rover.getLocation().getY()).isEqualTo(2);

        // given
        // when
        rover.turnLeft();
        rover.moveForward();

        // then
        Assertions.assertThat(rover.getPosition()).isEqualTo(Position.W);
        Assertions.assertThat(rover.getLocation().getX()).isEqualTo(0);
        Assertions.assertThat(rover.getLocation().getY()).isEqualTo(2);
    }

    @Test
    public void should_throws_exception_when_boundaries_violated_on_x_axis_and_starting_point() {

        // given

        // when
        rover.turnLeft();

        // then
        assertThrows(IllegalArgumentException.class, () -> rover.moveForward());
    }

    @Test
    public void should_throws_exception_when_boundaries_violated_on_y_axis_and_starting_point() {

        // given

        // when
        rover.turnLeft();
        rover.turnLeft();

        // then
        assertThrows(IllegalArgumentException.class, () -> rover.moveForward());
    }

    @Test
    public void should_throws_exception_when_boundaries_violated_on_x_axis_and_ending_point() {

        // given

        // when
        rover.turnRight();
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();

        // then
        assertThrows(IllegalArgumentException.class, () -> rover.moveForward());
    }

    @Test
    public void should_throws_exception_when_boundaries_violated_on_y_axis_and_ending_point() {

        // given

        // when
        rover.moveForward();
        rover.moveForward();
        rover.moveForward();

        // then
        assertThrows(IllegalArgumentException.class, () -> rover.moveForward());
    }
}