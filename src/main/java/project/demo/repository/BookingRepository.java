package project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.demo.entities.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking b " +
            "JOIN flight f on b.flight_id = f.id " +
            "JOIN city dest on dest.id = f.destination_city_id " +
            "JOIN city sour on sour.id = f.from_city_id " +
            "JOIN airline_ticket art on b.airline_ticket_id = art.id " +
            "WHERE (:fromCityName IS NULL OR sour.name = :fromCityName) " +
            "AND (:toCityName IS NULL OR dest.name = :toCityName)" +
            "AND (:departDate IS NULL OR art.depart_date > :departDate)" +
            "AND (:returnDate IS NULL OR art.return_date < :returnDate)" , nativeQuery = true)
    List<Booking> findBookingsByFilters(@Param("fromCityName") String fromCityName, @Param("toCityName") String toCity,
                                        @Param("departDate")Date departDate, @Param("returnDate") Date returnDate);

    @Query(value = "SELECT DISTINCT * FROM booking join user_bookings on id = bookings_id where user_id = :id", nativeQuery = true)
    List<Booking> getBookingsForUser(Long id);




    //AND (art.depart_date >= :departDate AND art.return_date <= :returnDate)

    @Query(value = "SELECT * from booking join user_bookings on id = bookings_id where user_id = :id",nativeQuery = true)
    List<Booking> getBookings(Long id);

    @Query(value = "SELECT COUNT(bookings_id) FROM user_bookings where user_id = :id", nativeQuery = true)
    int numberOfBookings(Long id);
}
