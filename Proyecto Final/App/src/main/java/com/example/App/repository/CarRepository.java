package com.example.App.repository;

import com.example.App.model.Car;
import com.example.App.model.Rate;
import com.example.App.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByCarPlate(String carPlate);

    List<Car> findAllByRegistrationYear(LocalDate registrationYear);

    List<Car> findAllByCarPlateAndRegistrationYear(String carPlate, LocalDate registrationYear);

    List<Car> findAllByRental(Rental rental);

    List<Car> findAllByRates(Rate rate);
}
