package com.example.App.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rental
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRent;

    private LocalDate startDate, endDate;
    private Double price;

    @ManyToOne
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

}
