package com.example.App.Services;

import com.example.App.model.Rate;
import com.example.App.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService{

    @Autowired private RateRepository repository;

    @Autowired private CarServiceImpl carService;

    @Override
    public Optional<Rate> create(Rate rate) {
        if (!repository.findById(rate.getIdRate()).isPresent())
            return Optional.ofNullable(repository.save(rate));
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Rate> update(Rate rate) {
        if(repository.findById(rate.getIdRate()).isPresent()){
            repository.save(rate);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Rate> delete(Rate rate) {
        if(repository.findById(rate.getIdRate()).isPresent()){
            repository.delete(rate);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Rate> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Rate> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Rate> findAllByPrice(Double price) {
        return repository.findAllByPrice(price);
    }

    @Override
    public List<Rate> findAllByStartDate(LocalDate startDate) {
        return repository.findAllByStartDate(startDate);
    }

    @Override
    public List<Rate> findAllByEndDate(LocalDate endDate) {
        return repository.findAllByEndDate(endDate);
    }

    @Override
    public List<Rate> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Rate> findAllByEndDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByEndDateBetween(startDate, endDate);
    }

    @Override
    public List<Rate> findAllByStartDateLessThanEqual(LocalDate startDate) {
        return repository.findAllByStartDateLessThanEqual(startDate);
    }

    @Override
    public List<Rate> findAllByCars(Integer id) {
        if (carService.findById(id).isPresent())
            return repository.findAllByCars(carService.findById(id).get());
        else
            return new ArrayList<>();
    }

    @Override
    public Optional<Rate> findOneByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate) {
        return repository.findOneByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
    }
}
