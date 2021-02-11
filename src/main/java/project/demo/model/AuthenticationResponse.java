package project.demo.model;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;
    private String isAdmin;


    public AuthenticationResponse(String jwt, String isAdmin){
        this.jwt = jwt;
        this.isAdmin = isAdmin;
    }


}
