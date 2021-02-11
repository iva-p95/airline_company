package project.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import project.demo.entities.Booking;
import project.demo.model.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Booking> bookings;

    public void removeBooking(List<Booking> bookingList){


        for (int i = 0; i < bookings.size(); i++){
            int j = 0;

            Booking b = bookings.get(i);
            System.out.println("B: " + b);
            System.out.println();
            Booking b1 = bookingList.get(j);
            System.out.println("B1: " + b1);
            System.out.println();

            if (b.getId().equals(b1.getId())){
                System.out.println("usla sam");
                b.setAirlineTicket(null);
                b.setFlight(null);
                bookings.set(i, null);
                Booking booking = bookings.get(i);
                System.out.println("JE LI NULL? : " + booking);

                j++;
            }

        }
    }



}
