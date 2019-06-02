package com.example.App.Services;

import com.example.App.model.Rate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RateService {

    /**
     * Create a new rate
     * @param rate
     * @return
     */
    Optional<Rate> create(Rate rate);

    /**
     * Modify a rate
     * @param rate
     * @return
     */
    ResponseEntity<Rate> update(Rate rate);

    /**
     * Delete a rate
     * @param rate
     * @return
     */
    ResponseEntity<Rate> delete(Rate rate);

    /**
     * Find a rate by id
     * @param id
     * @return
     */
    Optional<Rate> findById(Integer id);

    /**
     * Find all rates
     * @return
     */
    List<Rate> findAll();

    /**
     * Find all rates by price
     * @param price
     * @return
     */
    List<Rate> findAllByPrice(Double price);

    /**
     * Find all rates by car Id
     * @param id
     * @return
     */
    List<Rate> findAllByCars(Integer id);
}
