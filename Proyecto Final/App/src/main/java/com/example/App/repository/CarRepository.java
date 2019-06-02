package com.example.App.repository;

import com.example.App.model.Car;
import com.example.App.model.Rate;
import com.example.App.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select c from Car c join c.rental r where r.startDate between ?1 and ?2 order by r.price desc")
    List<Car> findMostProfitableCarInBetweenDate(LocalDate startDate, LocalDate endDate);
}
