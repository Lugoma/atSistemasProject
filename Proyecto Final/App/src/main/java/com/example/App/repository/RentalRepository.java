package com.example.App.repository;

import com.example.App.model.Car;
import com.example.App.model.Client;
import com.example.App.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findAllByStartDate(LocalDate startDate);

    List<Rental> findAllByEndDate(LocalDate endDate);

    List<Rental> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rental> findAllByEndDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rental> findAllByPrice(Double price);

    List<Rental> findAllByStartDateLessThanEqual(LocalDate startDate);

    List<Rental> findAllByClient(Client client);

    List<Rental> findAllByCar(Car car);

}
