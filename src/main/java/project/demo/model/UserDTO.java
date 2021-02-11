package project.demo.model;

import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private UserType type;
}
