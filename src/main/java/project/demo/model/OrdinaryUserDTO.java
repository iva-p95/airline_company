package project.demo.model;

import lombok.Data;
import project.demo.entities.Booking;

import java.util.List;

@Data
public class OrdinaryUserDTO {

    private String username;
    private UserType type;
    private List<Booking> bookingList;
}
