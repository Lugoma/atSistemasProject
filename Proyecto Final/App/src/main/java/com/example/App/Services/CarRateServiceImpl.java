package com.example.App.Services;

import com.example.App.model.Car;
import com.example.App.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRateServiceImpl implements CarRateService{

    @Autowired private CarServiceImpl carService;

    @Autowired private RateServiceImpl rateService;

    @Override
    public ResponseEntity<?> associate(Integer idCar, Integer idRate) {

        Optional<Car> car = carService.findById(idCar);
        Optional<Rate> rate = rateService.findById(idRate);

        if(car.isPresent() && rate.isPresent())
        {
            // No es necesario hacerlo con rate tambien ya que la base de datos lo asocia automaticamente
            if(car.get().getRates().contains(rate.get())) {
                car.get().getRates().add(rate.get());

                carService.update(car.get());

                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }


        return ResponseEntity.notFound().build();
    }
}
