package com.example.App.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCar;

    private String carPlate;

    private LocalDate registrationYear;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private Set<Rental> rental = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rate> rates = new ArrayList<>();

    public Car(Integer id, String plate, LocalDate regYear)
    {
        this.idCar = id;
        this.carPlate = plate;
        this.registrationYear = regYear;
    }
}
