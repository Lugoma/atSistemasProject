package com.example.App.repository;

import com.example.App.model.Car;
import com.example.App.model.Client;
import com.example.App.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findAllByPrice(Double price);

    List<Rental> findAllByClient(Client client);

    List<Rental> findAllByCar(Car car);

}
