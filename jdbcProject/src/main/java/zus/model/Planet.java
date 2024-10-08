package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class Planet {
    private final int planetId;
    private String planetName;
    private int nearestStarDistance;
    private int maxTemp;
    private int minTemp;
    private int gravFieldRange;
    private int rotSpeed;
    private boolean founded;
    private boolean satellite;
    private int deadPplUnder40Num;
    private LocalDate startDate;
    private LocalDate endDate;
}