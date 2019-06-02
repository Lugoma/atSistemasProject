package com.example.App.Services;

import org.springframework.http.ResponseEntity;

public interface CarRateService {

    /**
     * Associate a car with a rate by carId and rateId
     * @param idCar
     * @param idRate
     * @return
     */
    ResponseEntity<?> associate(Integer idCar, Integer idRate);
}
