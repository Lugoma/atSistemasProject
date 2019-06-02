package com.example.App.Services;


import com.example.App.model.Car;
import com.example.App.model.Rate;
import com.example.App.model.Rental;
import com.example.App.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService{

    @Autowired private RentalRepository repository;

    @Autowired private ClientServiceImpl clientService;

    @Autowired private CarServiceImpl carService;

    @Autowired private RateServiceImpl rateService;

    @Autowired private CarRateService carRateService;


    @Override
    public Optional<Rental> create(Rental rental) {
        if(!repository.findById(rental.getIdRent()).isPresent()) {
            if (Optional.ofNullable(rental.getClient()).isPresent()) {
                if (!clientService.findById(rental.getClient().getIdClient()).isPresent())
                    return Optional.empty();
            }else return Optional.empty();


            if (Optional.ofNullable(rental.getCar()).isPresent()) {
                if (!carService.findById(rental.getCar().getIdCar()).isPresent())
                    return Optional.empty();
            }else return Optional.empty();


            Optional<Car> car = carService.findById(rental.getCar().getIdCar());

            // Comprueba que la fecha de inicio del alquiler del coche no sea antes de la de vencimiento de otro alquiler de
            // ese mismo coche.
            for(Rental ren : car.get().getRental())
            {
                if (ren.getEndDate().isAfter(rental.getStartDate()))
                {
                    return Optional.empty();
                }
            }

            for (Rate rate :  car.get().getRates())
            {
                if(validateRate(rate, rental))
                    rental.setPrice(rate.getPrice());

            }

            return Optional.ofNullable(repository.save(rental));
        }

        return Optional.empty();
    }


    @Override
    public ResponseEntity<Rental> update(Rental rental) {
        if(repository.findById(rental.getIdRent()).isPresent())
        {
            if(Optional.ofNullable(rental.getClient()).isPresent()) {
                if (!clientService.findById(rental.getClient().getIdClient()).isPresent())
                    clientService.create(rental.getClient());
            }else return ResponseEntity.badRequest().build();

            if (Optional.ofNullable(rental.getCar()).isPresent()) {
                if (!carService.findById(rental.getCar().getIdCar()).isPresent())
                    carService.create(rental.getCar());
            }else return ResponseEntity.badRequest().build();


            Optional<Car> car = carService.findById(rental.getCar().getIdCar());

            for (Rate rate :  car.get().getRates())
            {
                if(validateRate(rate, rental))
                    rental.setPrice(rate.getPrice());

            }

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

    public boolean validateRate(Rate rate , Rental rental)
    {
        return (rate.getStartDate().isBefore(rental.getStartDate()) && rate.getEndDate().isAfter(rental.getEndDate()));
    }
}
