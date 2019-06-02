package com.example.App.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRate;

    private LocalDate startDate, endDate;

    private Double price;

    @ManyToMany(mappedBy = "rates", fetch = FetchType.LAZY)
    private List<Car> cars = new ArrayList<>();

    public Rate(Integer id, LocalDate startDate, LocalDate endDate, Double price)
    {
        this.idRate = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
