package project.demo.configuration;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAdmin {

    public static boolean isRole_admin(UserDetails user) {

        return user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public static boolean isRole_user(UserDetails user) {

        return user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
