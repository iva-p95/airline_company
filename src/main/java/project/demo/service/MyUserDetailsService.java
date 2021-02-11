package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.demo.model.MyUserDetails;
import project.demo.entities.User;
import project.demo.model.UserType;
import project.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User name: " + username + " not found");

        }
        MyUserDetails myUserDetails = new MyUserDetails(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
        myUserDetails.setUser(user);
        return myUserDetails;
       // return new MyUserDetails(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));

    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user){
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(user.getUserType().equals(UserType.ADMIN)){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));


        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }







}
