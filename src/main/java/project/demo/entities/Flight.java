package project.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import project.demo.entities.AirlineTicket;
import project.demo.entities.City;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany
    @ToString.Exclude
    private List<AirlineTicket> ticketList;
    @OneToOne
    @JoinColumn(name = "fromCity_id", referencedColumnName = "id")
    private City from;
    @OneToOne
    @JoinColumn(name = "destinationCity_id", referencedColumnName = "id")
    private City destination;

}
