package com.example.App.repository;

import com.example.App.model.Car;
import com.example.App.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

    List<Rate> findAllByPrice(Double price);

    List<Rate> findAllByCars(Car car);


}
