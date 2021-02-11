package project.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isAvailable;
    @OneToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "airlineTicket_id", referencedColumnName = "id")
    private AirlineTicket airlineTicket;





}
