package project.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.demo.entities.Flight;
import project.demo.service.FlightService;

import java.util.List;

@RestController
@RequestMapping("/flight")
@CrossOrigin
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE
         )
    public List<Flight>  getAll(){
        return flightService.getAll();
    }
}
