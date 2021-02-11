package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.entities.Airline;
import project.demo.entities.AirlineTicket;
import project.demo.repository.AirlineRepository;

import java.util.List;

@Service
public class AirlineService {
    @Autowired
    AirlineRepository airlineRepository;


    public List<Airline> getAll(){
        return airlineRepository.findAll();
    }

    public Airline addAirline(Airline airline){
        if(!airline.getName().matches("^[a-zA-Z0-9 ]+$")){
            throw new RuntimeException("Invalid properties");
        }
        return airlineRepository.save(airline);
    }

//[A-Za-z0-9]+
}
