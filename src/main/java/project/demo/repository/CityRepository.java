package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.demo.entities.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT DISTINCT name FROM city",nativeQuery = true)
    List<String> findAll1();
}
