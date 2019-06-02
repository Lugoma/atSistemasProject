package com.example.App.Services;

import com.example.App.model.Rate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RateService {

    Optional<Rate> create(Rate rate);

    ResponseEntity<Rate> update(Rate rate);

    ResponseEntity<Rate> delete(Rate rate);

    Optional<Rate> findById(Integer id);

    List<Rate> findAll();

    List<Rate> findAllByStartDate(LocalDate startDate);

    List<Rate> findAllByEndDate(LocalDate endDate);

    List<Rate> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rate> findAllByEndDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rate> findAllByPrice(Double price);

    List<Rate> findAllByStartDateLessThanEqual(LocalDate startDate);

    List<Rate> findAllByCars(Integer id);

    Optional<Rate> findOneByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);
}
