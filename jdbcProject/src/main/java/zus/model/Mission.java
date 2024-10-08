package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class Mission {

    private final int missionId;
    private LocalDate missionStartDate;
    private LocalDate missionEndDate;

}