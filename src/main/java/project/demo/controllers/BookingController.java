package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.demo.configuration.UserAdmin;
import project.demo.entities.Booking;
import project.demo.model.*;
import project.demo.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping(value = "/filtered", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getBookings(@RequestBody BookingFilterDTO filter) {
        List<Booking> filtered = null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (UserAdmin.isRole_user(userDetails)) {
            filtered = bookingService.getFilteredBookings(filter);
            //return bookingService.getFilteredBookings(filter);
        }
        return filtered;
    }


    @GetMapping(value = "/bfu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> bfu(){
        return bookingService.getBookingsForUser();
    }

    @GetMapping("/number")
    public int numberOfB(){
        return bookingService.numberOfBookings();
    }

    @GetMapping(value = "/all")
    public List<Booking> getBookings(){
        return bookingService.getBookings();

    }



}
