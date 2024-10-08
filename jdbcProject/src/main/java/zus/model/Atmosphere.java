package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Atmosphere {

    private final int atmosphereId;
    private float o2Percentage;

}