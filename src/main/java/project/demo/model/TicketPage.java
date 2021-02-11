package project.demo.model;

import lombok.Data;
import org.springframework.data.domain.Sort;
import project.demo.entities.Airline;

@Data
public class TicketPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "airline";
}
