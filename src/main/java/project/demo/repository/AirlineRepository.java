package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.demo.entities.Airline;
import project.demo.entities.AirlineTicket;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Long> {


}

//SELECT * from airline_ticket gg JOIN airline aa ON gg.airline_id = aa.id WHERE aa.id = :id