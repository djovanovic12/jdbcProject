package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Gas {

    private final int gasId;
    private String gasSymbol;
    private float gasPercentage;
    private boolean isSoluble;
    private boolean isDeadly;

}