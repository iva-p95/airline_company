package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.demo.entities.AirlineTicket;

import java.util.List;

public interface AirlineTicketRepository extends JpaRepository<AirlineTicket, Long> {


    @Query(value = "SELECT * FROM airline_ticket att " +
            "JOIN airline a on att.airline_id = a.id " +
            "JOIN flight f on att.flight_id = f.id " , nativeQuery = true)
    List<AirlineTicket> getAllTickets();


    @Query(value = "SELECT * FROM airline_ticket WHERE one_way = 1", nativeQuery = true)
    List<AirlineTicket> getAllOneWay();

    @Query(value = "SELECT * FROM airline_ticket WHERE one_way = 0", nativeQuery = true)
    List<AirlineTicket> getAllReturnTickets();

    @Query(value = "SELECT * from airline_ticket  WHERE airline_id = :id", nativeQuery = true)
    List<AirlineTicket> getTickets(Long id);


}
