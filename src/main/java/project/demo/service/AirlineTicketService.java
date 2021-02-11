package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.demo.configuration.UserAdmin;
import project.demo.entities.AirlineTicket;
import project.demo.model.TicketPage;
import project.demo.repository.AirlineRepository;
import project.demo.repository.AirlineTicketRepository;
import project.demo.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineTicketService {

    @Autowired
    private AirlineTicketRepository airlineTicketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    public AirlineTicket addTicket(AirlineTicket airlineTicket){
        AirlineTicket airlineTicket1 = null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(UserAdmin.isRole_admin(userDetails)){
            flightRepository.save(airlineTicket.getFlight());
            airlineRepository.save(airlineTicket.getAirline());
           airlineTicket1 = airlineTicketRepository.save(airlineTicket);
        }
        //else {
          //  return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       // }
        return airlineTicket1;

    }

    public List<AirlineTicket> getAll(){
        return airlineTicketRepository.getAllTickets();
    }

    public List<AirlineTicket> getAllOneWay(){
        return airlineTicketRepository.getAllOneWay();

    }

    public List<AirlineTicket> getAllReturnTickets(){
        return airlineTicketRepository.getAllReturnTickets();
    }

    public void deleteById(Long id){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(UserAdmin.isRole_admin(userDetails)){
            airlineTicketRepository.deleteById(id);
        }

    }


    public Optional<AirlineTicket> findById(Long id){


        return airlineTicketRepository.findById(id);
    }



    public AirlineTicket update(AirlineTicket airlineTicket){
        return airlineTicketRepository.save(airlineTicket);
    }


    public Page<AirlineTicket> getTickects(TicketPage ticketPage){
        Sort sort = Sort.by(ticketPage.getSortDirection(), ticketPage.getSortBy());
        Pageable pageable = PageRequest.of(ticketPage.getPageNumber(),
                ticketPage.getPageSize(), sort);
        return airlineTicketRepository.findAll(pageable);

    }


    public AirlineTicket getTicket(Long id){
        return airlineTicketRepository.findById(id).get();
    }

    public List<AirlineTicket> getTicketsForA(Long id){
        return airlineTicketRepository.getTickets(id);
    }
}
