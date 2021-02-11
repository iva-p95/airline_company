package project.demo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.demo.entities.*;
import project.demo.model.*;
import project.demo.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirlineTicketRepository airlineTicketRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Data...");

        List<Airline> airlines = new ArrayList<>();
        Airline airline = new Airline();
        airline.setName("Air Serbia");
        Airline airline1 = new Airline();
        airline1.setName("Air France");
        airlines.add(airline);
        airlines.add(airline1);
        airlineRepository.saveAll(airlines);


        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setName("Belgrade");
        City city1 = new City();
        city1.setName("Paris");
        City city2 = new City();
        city2.setName("Podgorica");
        cities.add(city);
        cities.add(city1);
        cities.add(city2);
        cityRepository.saveAll(cities);

        List<Flight> flights = new ArrayList<>();

        Flight flight = new Flight();
        flight.setFrom(city2);
        flight.setDestination(city);

        Flight flight1 = new Flight();
        flight1.setFrom(city1);
        flight1.setDestination(city2);

        Flight flight2 = new Flight();
        flight2.setFrom(city2);
        flight2.setDestination(city);

        List<AirlineTicket> ticketList = new ArrayList<>();

        AirlineTicket airlineTicket = new AirlineTicket();
        airlineTicket.setAirline(airline);
        airlineTicket.setOneWay(false);
        airlineTicket.setReturnDate(new Date());

        airlineTicket.setNumberOfTickets(3L);
        airlineTicket.setDepartDate(new Date());

        AirlineTicket airlineTicket1 = new AirlineTicket();
        airlineTicket1.setAirline(airline1);
        airlineTicket1.setOneWay(true);
        //airlineTicket1.setReturnDate(new Date());

        airlineTicket1.setNumberOfTickets(5L);
        airlineTicket1.setDepartDate(new Date());

        AirlineTicket airlineTicket2 = new AirlineTicket();
        airlineTicket2.setAirline(airline1);
        airlineTicket2.setOneWay(false);
        airlineTicket2.setReturnDate(new Date());

        airlineTicket2.setNumberOfTickets(6L);
        airlineTicket2.setDepartDate(new Date());



        flight.setTicketList(ticketList);
        flight1.setTicketList(ticketList);
        flight2.setTicketList(ticketList);

        flights.add(flight);
        flights.add(flight1);
        flights.add(flight2);

        flightRepository.saveAll(flights);

        airlineTicket.setFlight(flight);
        ticketList.add(airlineTicket);

        airlineTicket1.setFlight(flight1);
        ticketList.add(airlineTicket1);

        airlineTicket2.setFlight(flight2);
        ticketList.add(airlineTicket2);
        airlineTicketRepository.saveAll(ticketList);

        List<Booking> bookingList = new ArrayList<>();
        Booking booking = new Booking();
        booking.setIsAvailable(true);
        booking.setFlight(flight);
        booking.setAirlineTicket(airlineTicket);
        bookingList.add(booking);

        Booking booking1 = new Booking();
        booking1.setIsAvailable(false);
        booking1.setFlight(flight1);
        booking1.setAirlineTicket(airlineTicket1);
        bookingList.add(booking1);

        bookingRepository.saveAll(bookingList);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("iva");
        user.setUserType(UserType.USER);
        String encryptedPass = passwordEncoder.encode("12345");
        user.setPassword(encryptedPass);
        user.setBookings(bookingList);
        users.add(user);

        User user1 = new User();
        user1.setUsername("mika");
        user1.setUserType(UserType.ADMIN);
        String encryptedPass1 = passwordEncoder.encode("123");
        user1.setPassword(encryptedPass1);
        users.add(user1);

        userRepository.saveAll(users);


    }
}
