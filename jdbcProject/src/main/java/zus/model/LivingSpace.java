package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LivingSpace {

    private final int livingSpaceId;
    private String livingSpaceName;
    private int numberOfPeople;
    private boolean occupied;
}