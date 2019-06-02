package com.example.App.Services;

import com.example.App.model.Car;
import com.example.App.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired private CarRepository repository;

    @Autowired private RentalServiceImpl rentalService;

    @Autowired private RateServiceImpl rateService;

    @Override
    public Optional<Car> create(Car car) {
        if (!repository.findById(car.getIdCar()).isPresent())
            return Optional.ofNullable(repository.save(car));
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Car> update(Car car) {
        if(repository.findById(car.getIdCar()).isPresent())
        {
            repository.save(car);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Car> delete(Car car) {

        if(repository.findById(car.getIdCar()).isPresent())
            {
                repository.delete(car);
                return ResponseEntity.ok().build();
            }

        return ResponseEntity.notFound().build();

    }

    @Override
    public Optional<Car> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Car> findAllByCarPlate(String carPlate) {
        return repository.findAllByCarPlate(carPlate);
    }

    @Override
    public List<Car> findAllByRegistrationYear(LocalDate date) {
        return repository.findAllByRegistrationYear(date);
    }

    @Override
    public List<Car> findAllByCarPLateAndRegistrationYear(String carPLate, LocalDate date) {
        return repository.findAllByCarPlateAndRegistrationYear(carPLate, date);
    }

    @Override
    public List<Car> findAllByRental(Integer id) {
        if (rentalService.findById(id).isPresent())
            return repository.findAllByRental(rentalService.findById(id).get());
        else
            return new ArrayList<>();
    }

    @Override
    public List<Car> findAllByRates(Integer id) {
        if (rateService.findById(id).isPresent())
            return repository.findAllByRates(rateService.findById(id).get());
        else
            return new ArrayList<>();
    }

    @Override
    public Optional<Car> findMostProfitableCarInBetweenDate(LocalDate startDate, LocalDate endDate) {
        List<Car> list = repository.findMostProfitableCarInBetweenDate(startDate, endDate);

        if (list.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(list.get(0));
    }
}
