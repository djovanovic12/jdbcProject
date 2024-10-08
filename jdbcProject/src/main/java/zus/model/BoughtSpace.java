package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BoughtSpace {
    String planetName;
    String livingSpaceName;
    String spaceshipName;
    int numberOfPeople;
}
