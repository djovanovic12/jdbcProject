package zus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Human {

    private final int humanId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean hasPassport;

}