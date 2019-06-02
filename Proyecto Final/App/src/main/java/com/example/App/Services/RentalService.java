package com.example.App.Services;

import com.example.App.model.Rental;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalService {

    Optional<Rental> create(Rental rental);

    ResponseEntity<Rental> update(Rental rental);

    ResponseEntity<Rental> delete(Rental rental);

    Optional<Rental> findById(Integer id);

    List<Rental> findAll();

    List<Rental> findAllByStartDate(LocalDate startDate);

    List<Rental> findAllByEndDate(LocalDate endDate);

    List<Rental> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rental> findAllByEndDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rental> findAllByPrice(Double price);

    List<Rental> findAllByStartDateLessThanEqual(LocalDate startDate);

    List<Rental> findAllByClient(Integer id);

    List<Rental> findAllByCar(Integer id);
}
