package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class Spaceship {

    private final int spaceshipId;
    private String spaceshipName;
    private LocalDate departureDate;
    private int departureHours;
    private int departureMinutes;
    private int numberOfSeats;

}