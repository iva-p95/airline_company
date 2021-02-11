package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.demo.entities.Airline;
import project.demo.entities.AirlineTicket;
import project.demo.service.AirlineService;

import java.util.List;

@RestController
@RequestMapping("/airline")
@CrossOrigin
public class AirlineController {

    @Autowired
    private AirlineService airlineService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airline> getAll(){
        return airlineService.getAll();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Airline addAirline(@RequestBody  Airline airline){
        return airlineService.addAirline(airline);
    }



}
