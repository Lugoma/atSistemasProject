package com.example.App.Services;

import com.example.App.model.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RentalService {

    /**
     * Create a new rental
     * @param
     * @return
     */
    Optional<Rental> create(Rental rental);

    /**
     * Modify an existing rental
     * @param rental
     * @return
     */
    ResponseEntity<Rental> update(Rental rental);

    /**
     * Delete an existing rental
     * @param rental
     * @return
     */
    ResponseEntity<Rental> delete(Rental rental);

    /**
     * Find a rental by Id
     * @param id
     * @return
     */
    Optional<Rental> findById(Integer id);

    /**
     * Find all rental
     * @return
     */
    List<Rental> findAll();

    /**
     * Find all rental by price
     * @param price
     * @return
     */
    List<Rental> findAllByPrice(Double price);

    /**
     * Find all rental by client Id
     * @param id
     * @return
     */
    List<Rental> findAllByClient(Integer id);

    /**
     * Find all rental by car Id
     * @param id
     * @return
     */
    List<Rental> findAllByCar(Integer id);
}
