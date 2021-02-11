package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.demo.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
