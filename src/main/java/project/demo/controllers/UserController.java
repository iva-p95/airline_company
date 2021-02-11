package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.demo.configuration.UserAdmin;
import project.demo.entities.User;
import project.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user){
       User user1 = null;
       UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if (UserAdmin.isRole_admin(userDetails)) {

           user1 = userService.addUser(user);
       }
       return user1;
   }

    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTypes(){
        return userService.getTypes();

    }


}
