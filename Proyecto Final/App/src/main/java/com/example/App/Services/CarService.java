package com.example.App.Services;


import com.example.App.model.Car;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<Car> create(Car car);

    ResponseEntity<Car> update(Car car);

    ResponseEntity<Car> delete(Car car);

    Optional<Car> findById(Integer id);

    List<Car> findAll();

    List<Car> findAllByCarPlate(String carPlate);

    List<Car> findAllByRegistrationYear(LocalDate date);

    List<Car> findAllByCarPLateAndRegistrationYear(String carPLate, LocalDate date);

    List<Car> findAllByRental(Integer id);

    List<Car> findAllByRates(Integer id);


}
