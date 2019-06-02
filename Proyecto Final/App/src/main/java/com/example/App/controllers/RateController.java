package com.example.App.controllers;

import com.example.App.Components.DateStringMapper;
import com.example.App.Components.MapperServiceRate;
import com.example.App.DTO.RateDto;
import com.example.App.Services.CarRateServiceImpl;
import com.example.App.Services.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rate")
@RestController
public class RateController {

    @Autowired private MapperServiceRate mapper;

    @Autowired private RateServiceImpl service;

    @Autowired private DateStringMapper dateMapper;

    @Autowired private CarRateServiceImpl carRateService;

    /* GET */

    @GetMapping("{id}")
    public ResponseEntity<RateDto> getById(@PathVariable("id") Integer id)
    {
        return service.findById(id)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<RateDto> getAll(@RequestParam(value = "price", required = false) Double price)
    {
        if (Optional.ofNullable(price).isPresent())
            return mapper.mapListDaoToDto(service.findAllByPrice(price));

        return mapper.mapListDaoToDto(service.findAll());
    }

    @GetMapping("/car/{id}")
    public List<RateDto> getByCar(@PathVariable("id") Integer id)
    {
        return mapper.mapListDaoToDto(service.findAllByCars(id));
    }


    @GetMapping("/{idRate}/car/{idCar}")
    public ResponseEntity<?> associateCarRate(@PathVariable("idRate") Integer idRate,
                                              @PathVariable("idCar") Integer idCar)
    {
        if (Optional.ofNullable(idCar).isPresent() && Optional.ofNullable(idRate).isPresent())
            return carRateService.associate(idCar, idRate);
        return ResponseEntity.badRequest().build();
    }

    /* POST */

    @PostMapping
    public ResponseEntity<RateDto> post(@RequestBody RateDto rate)
    {
        return Optional.ofNullable(rate)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /* PUT */
    @PutMapping
    public  ResponseEntity<?> put(@RequestBody RateDto rate)
    {
        if(Optional.ofNullable(rate).isPresent())
            if(mapper.mapDtoToDao(rate).isPresent())
                return service.update(mapper.mapDtoToDao(rate).get());

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody RateDto rate)
    {
        if(Optional.ofNullable(rate).isPresent())
            if(mapper.mapDtoToDao(rate).isPresent())
                return service.delete(mapper.mapDtoToDao(rate).get());

        return ResponseEntity.badRequest().build();
    }
}
