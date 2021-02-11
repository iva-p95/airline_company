package project.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class AirlineTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;
    private Boolean oneWay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date departDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date returnDate;
    @OneToOne
    @JoinColumn(name = "flightId", referencedColumnName = "id")
    private Flight flight;
    private Long numberOfTickets;

    @JsonIgnore
    @OneToMany(mappedBy = "airlineTicket" , cascade = CascadeType.REMOVE)
    //, cascade = CascadeType.REMOVE
    @ToString.Exclude
    private List<Booking> bookingList;
}
