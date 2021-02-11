package project.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

}
