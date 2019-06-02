package com.example.App.Services;


import com.example.App.model.Rate;
import com.example.App.model.Rental;
import com.example.App.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService{

    @Autowired private RentalRepository repository;

    @Autowired private ClientServiceImpl clientService;

    @Autowired private CarServiceImpl carService;

    @Autowired private RateServiceImpl rateService;


    @Override
    public Optional<Rental> create(Rental rental) {
        if(!repository.findById(rental.getIdRent()).isPresent()) {
            if (Optional.ofNullable(rental.getClient()).isPresent())
                if (!clientService.findById(rental.getClient().getIdClient()).isPresent())
                    //clientService.create(rental.getClient());
                    return Optional.empty();
                else
                {
                    clientService.findById(rental.getClient().getIdClient()).get().getRents().add(rental);
                    clientService.update(rental.getClient());
                }

            if (Optional.ofNullable(rental.getCar()).isPresent())
                if (!carService.findById(rental.getCar().getIdCar()).isPresent())
                    //carService.create(rental.getCar());
                    return Optional.empty();

                else
                {
                    carService.findById(rental.getCar().getIdCar()).get().getRental().add(rental);
                    carService.update(rental.getCar());
                }

            Optional<Rate> activeRate = activeRate(rental);
            if (activeRate.isPresent())
                rental.setPrice(activeRate.get().getPrice());

            return Optional.ofNullable(repository.save(rental));
        }

        return Optional.empty();
    }


    @Override
    public ResponseEntity<Rental> update(Rental rental) {
        if(repository.findById(rental.getIdRent()).isPresent())
        {
            if(Optional.ofNullable(rental.getClient()).isPresent())
                if (!clientService.findById(rental.getClient().getIdClient()).isPresent())
                    clientService.create(rental.getClient());

            if (Optional.ofNullable(rental.getCar()).isPresent())
                if(!carService.findById(rental.getCar().getIdCar()).isPresent())
                    carService.create(rental.getCar());


            Optional<Rate> activeRate = activeRate(rental);
            if (activeRate.isPresent())
                rental.setPrice(activeRate.get().getPrice());

            repository.save(rental);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Rental> delete(Rental rental) {
        if(repository.findById(rental.getIdRent()).isPresent())
        {
            repository.delete(rental);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Rental> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Rental> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Rental> findAllByPrice(Double price) {
        return repository.findAllByPrice(price);
    }

    @Override
    public List<Rental> findAllByEndDate(LocalDate endDate) {
        return repository.findAllByEndDate(endDate);
    }

    @Override
    public List<Rental> findAllByStartDate(LocalDate startDate) {
        return repository.findAllByStartDate(startDate);
    }

    @Override
    public List<Rental> findAllByEndDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByEndDateBetween(startDate, endDate);
    }

    @Override
    public List<Rental> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Rental> findAllByStartDateLessThanEqual(LocalDate startDate) {
        return repository.findAllByStartDateLessThanEqual(startDate);
    }

    @Override
    public List<Rental> findAllByClient(Integer id) {
        if (clientService.findById(id).isPresent())
            return repository.findAllByClient(clientService.findById(id).get());

        return new ArrayList<>();
    }

    @Override
    public List<Rental> findAllByCar(Integer id) {
        if (carService.findById(id).isPresent())
            return repository.findAllByCar(carService.findById(id).get());

        return new ArrayList<>();
    }


    private Optional<Rate> activeRate(Rental rental)
    {
        return rateService.findOneByStartDateLessThanEqualAndEndDateGreaterThanEqual(rental.getStartDate(), rental.getEndDate());
    }


}
