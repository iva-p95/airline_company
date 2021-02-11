package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.demo.entities.Booking;
import project.demo.entities.User;
import project.demo.model.*;
import project.demo.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getFilteredBookings(BookingFilterDTO filterDTO){
        System.out.println(filterDTO.getFromCityName()+ " " + filterDTO.getToCityName() + " " +
                filterDTO.getDepartDate()+ " " +filterDTO.getReturnDate());
        return bookingRepository.findBookingsByFilters(filterDTO.getFromCityName(), filterDTO.getToCityName(),
                filterDTO.getDepartDate(),filterDTO.getReturnDate());
    }

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }


  public void delete(Booking booking){
      System.out.println("BOOKINGSERVICE DELETE " + booking);
        bookingRepository.delete(booking);
  }

  public List<Booking> getBookingsForUser(){
      MyUserDetails myUserDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user = myUserDetails.getUser();
      return bookingRepository.getBookings(user.getId());
  }

  public int numberOfBookings(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = myUserDetails.getUser();
        return bookingRepository.numberOfBookings(user.getId());
  }

  public List<Booking> getBookings(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = myUserDetails.getUser();
        return bookingRepository.getBookingsForUser(user.getId());
    }
}
