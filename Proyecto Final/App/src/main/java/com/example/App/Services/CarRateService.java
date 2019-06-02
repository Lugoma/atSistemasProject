package com.example.App.Services;

import org.springframework.http.ResponseEntity;

public interface CarRateService {

    ResponseEntity<?> associate(Integer idCar, Integer idRate);
}
