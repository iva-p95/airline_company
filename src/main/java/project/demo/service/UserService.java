package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.demo.entities.User;
import project.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user)  {
        String pass = user.getPassword();
        if(pass.length() < 6 || !pass.matches("[A-Za-z0-9 ]+")){
            throw new RuntimeException("Invalid properties");
        }
        String encryptedPass = passwordEncoder.encode(pass);
        user.setPassword(encryptedPass);
        return userRepository.save(user);
    }


    public List<String> getTypes(){
        return userRepository.getTypes();
    }

     public User update(User user){
      return   userRepository.save(user);
     }



}
