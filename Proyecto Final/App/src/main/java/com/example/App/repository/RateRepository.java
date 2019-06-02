package com.example.App.repository;

import com.example.App.model.Car;
import com.example.App.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

    List<Rate> findAllByStartDate(LocalDate startDate);

    List<Rate> findAllByEndDate(LocalDate endDate);

    List<Rate> findAllByPrice(Double price);

    List<Rate> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rate> findAllByEndDateBetween(LocalDate startDate, LocalDate endDate);

    List<Rate> findAllByStartDateLessThanEqual(LocalDate startDate);

    List<Rate> findAllByCars(Car car);

    Optional<Rate> findOneByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);

}
