package com.example.App.Services;


import com.example.App.model.Car;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarService {

    /**
     * Create a car
     * @param car
     * @return
     */
    Optional<Car> create(Car car);

    /**
     * Modify a car
     * @param car
     * @return
     */
    ResponseEntity<Car> update(Car car);

    /**
     * Delete a car
     * @param car
     * @return
     */
    ResponseEntity<Car> delete(Car car);

    /**
     * Find a car by Id
     * @param id
     * @return
     */
    Optional<Car> findById(Integer id);

    /**
     * Find all cars
     * @return
     */
    List<Car> findAll();


    /**
     * Find al cars by carPlate. It is assumed that carPlate is not an unique value
     * @param carPlate
     * @return
     */
    List<Car> findAllByCarPlate(String carPlate);

    /**
     * Find all cars by registrationYear
     * @param date
     * @return
     */
    List<Car> findAllByRegistrationYear(LocalDate date);

    /**
     * Find all cars by carPLate and registrationYear
     * @param carPLate
     * @param date
     * @return
     */
    List<Car> findAllByCarPLateAndRegistrationYear(String carPLate, LocalDate date);

    /**
     * Find all cars by rental Id
     * @param id
     * @return
     */
    List<Car> findAllByRental(Integer id);

    /**
     * Find all cars by rental Id
     * @param id
     * @return
     */
    List<Car> findAllByRates(Integer id);

    /**
     * Find the most profitable car in between two dates
     * @param startDate
     * @param endDate
     * @return
     */
    Optional<Car> findMostProfitableCarInBetweenDate(LocalDate startDate, LocalDate endDate);

}
