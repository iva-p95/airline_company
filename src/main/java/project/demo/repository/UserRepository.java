package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.demo.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT DISTINCT user_type from USER WHERE user_type LIKE 'ADMIN'  OR user_type LIKE 'USER' ", nativeQuery = true)
    List<String> getTypes();



}
