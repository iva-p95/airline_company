package project.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class BookingFilterDTO {
    private String fromCityName;
    private String toCityName;
    private Date departDate;
    private Date returnDate;
}
