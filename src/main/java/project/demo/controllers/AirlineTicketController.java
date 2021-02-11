package project.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.demo.entities.Airline;
import project.demo.entities.AirlineTicket;
import project.demo.entities.Booking;
import project.demo.entities.User;
import project.demo.model.*;
import project.demo.service.AirlineTicketService;
import project.demo.service.BookingService;
import project.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class AirlineTicketController {

    @Autowired
    private AirlineTicketService airlineTicketService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AirlineTicket addTicket(@RequestBody AirlineTicket airlineTicket){
        return airlineTicketService.addTicket(airlineTicket);

    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AirlineTicket> getAllTickets(){

        List<AirlineTicket> ticketList = airlineTicketService.getAll();
        System.out.println("LISTA KARATA: " + ticketList);
        return ticketList;

    }

    @GetMapping(value = "/oneWay", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AirlineTicket> getAllOneWay(){
        return airlineTicketService.getAllOneWay();
    }

    @GetMapping(value = "/returnTickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AirlineTicket> getReturnTickets(){
        return airlineTicketService.getAllReturnTickets();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDetails.getUser();
        Optional<AirlineTicket> optionalAirlineTicket = airlineTicketService.findById(id);
        if(optionalAirlineTicket.isPresent()){
            AirlineTicket airlineTicket = optionalAirlineTicket.get();

            List<Booking> bookingList = airlineTicket.getBookingList();

            if(bookingList.size() > 0){
                loggedInUser.removeBooking(bookingList);

            }


            airlineTicket.setAirline(null);
            airlineTicket.setFlight(null);

            userService.update(loggedInUser);
            airlineTicketService.deleteById(id);

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
         produces = MediaType.APPLICATION_JSON_VALUE)
    public AirlineTicket updateTicket(@RequestBody AirlineTicket airlineTicket){

        return airlineTicketService.addTicket(airlineTicket);
    }

    @PostMapping(value = "/reserve/{number}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> makeReservation(@RequestBody AirlineTicket airlineTicket, @PathVariable("number") int numberOfTickets){
        System.out.println("pozvala");

        Optional<AirlineTicket> optionalAirlineTicket = airlineTicketService.findById(airlineTicket.getId());
        AirlineTicket airlineTicket1 = null;
        if (optionalAirlineTicket.isPresent()){
            airlineTicket1 = optionalAirlineTicket.get();
            System.out.println("AT1: " + airlineTicket1);
            System.out.println("ATLIST: " + airlineTicket1.getBookingList());
            System.out.println();
        }

        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(numberOfTickets <= airlineTicket1.getNumberOfTickets()){

            for (int i = 0; i < numberOfTickets; i++){
                Booking booking = new Booking();
                booking.setFlight(airlineTicket1.gflightetFlight());
                booking.setAirlineTicket(airlineTicket1);
                booking.setIsAvailable(true);
                user.getUser().getBookings().add(booking);
                bookingService.saveBooking(booking);

            }

            } else {

               throw new RuntimeException("Can not make reservation");

            }


        airlineTicket1.setNumberOfTickets(airlineTicket1.getNumberOfTickets() - numberOfTickets);
        airlineTicketService.update(airlineTicket1);

        if (airlineTicket1.getNumberOfTickets() == 0) {
            List<Booking> bookingList = airlineTicket1.getBookingList();
            user.getUser().removeBooking(bookingList);

            airlineTicket1.setAirline(null);
            airlineTicket1.setFlight(null);

            userService.update(user.getUser());
            airlineTicketService.deleteById(airlineTicket1.getId());
        }

        userService.update(user.getUser());
        return ResponseEntity.ok().build();


    }

   @GetMapping (value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AirlineTicket> getTickets(@Validated TicketPage ticketPage){
        return airlineTicketService.getTickects(ticketPage);
    }

    @GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AirlineTicket getById(@PathVariable("id") Long id){
        return airlineTicketService.getTicket(id);
    }

    @GetMapping(value = "/ticketsAll/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AirlineTicket> getTicketsForA(@PathVariable Long id){
        return airlineTicketService.getTicketsForA(id);
    }
}
