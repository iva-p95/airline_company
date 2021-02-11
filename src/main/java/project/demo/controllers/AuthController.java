package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import project.demo.model.AuthenticationRequest;
import project.demo.model.AuthenticationResponse;
import project.demo.model.MyUserDetails;
import project.demo.model.UserType;
import project.demo.service.MyUserDetailsService;
import project.demo.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {

        try {
            System.out.println(authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));


        } catch (BadCredentialsException e){
            e.printStackTrace();
            ResponseEntity.status(401).build();
            //return ResponseEntity.status(401).build();
        }

        final MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String isAdmin = "";
        UserType type = userDetails.getUser().getUserType();
        int numberOfBookings = userDetails.getUser().getBookings().size();
        final String jwt = jwtUtil.generateToken(userDetails);

        if(type.equals(UserType.ADMIN)){
            isAdmin = "ADMIN";

        } else {
            isAdmin = "USER";

        }
        return new AuthenticationResponse(jwt, isAdmin);


    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:login";
    }
}
