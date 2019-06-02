package com.example.App.controllers;

import com.example.App.Components.DateStringMapper;
import com.example.App.Components.MapperServiceCar;
import com.example.App.DTO.CarDto;
import com.example.App.Services.CarRateServiceImpl;
import com.example.App.Services.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/car")
@RestController
public class CarController {

    @Autowired private CarServiceImpl service;

    @Autowired private MapperServiceCar mapper;

    @Autowired private DateStringMapper dateMapper;

    @Autowired private CarRateServiceImpl carRateService;

    /* GET */
    @GetMapping("{id}")
    public ResponseEntity<CarDto> getById(@PathVariable("id") Integer id)
    {
        return service.findById(id)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CarDto> getAll(@RequestParam(value = "carPlate", required = false) String carPlate,
                               @RequestParam(value = "registrationYear", required = false) String registrationYear)
    {

        List<CarDto> list;

        if (Optional.ofNullable(carPlate).isPresent() && Optional.ofNullable(registrationYear).isPresent())
            list = (mapper.mapListDaoToDto(service.findAllByCarPLateAndRegistrationYear(carPlate, dateMapper.map(registrationYear))));
        else
            if (Optional.ofNullable(carPlate).isPresent())
                list = mapper.mapListDaoToDto(service.findAllByCarPlate(carPlate));
            else if (Optional.ofNullable(registrationYear).isPresent())
                list = mapper.mapListDaoToDto(service.findAllByRegistrationYear(dateMapper.map(registrationYear)));
            else list = mapper.mapListDaoToDto(service.findAll());

        return list;
    }

    @GetMapping("/rental/{id}")
    public List<CarDto> getByRental(@PathVariable("id") Integer id)
    {
        return mapper.mapListDaoToDto(service.findAllByRental(id));
    }

    @GetMapping("/rate/{id}")
    public List<CarDto> getByRatel(@PathVariable("id") Integer id)
    {
        return mapper.mapListDaoToDto(service.findAllByRates(id));
    }


    @GetMapping("/{idCar}/rate/{idRate}")
    public ResponseEntity<?> associateCarRate(@PathVariable("idCar") Integer idCar,
                                              @PathVariable("idRate") Integer idRate)
    {
        if (Optional.ofNullable(idCar).isPresent() && Optional.ofNullable(idRate).isPresent())
            return carRateService.associate(idCar, idRate);
        return ResponseEntity.badRequest().build();
    }

    /* POST */

    @PostMapping
    public ResponseEntity<CarDto> post(@RequestBody CarDto car)
    {
        return Optional.ofNullable(car)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /* PUT */
    @PutMapping
    public ResponseEntity<?> put(@RequestBody CarDto car)
    {
        if(Optional.ofNullable(car).isPresent())
            if(mapper.mapDtoToDao(car).isPresent())
                return service.update(mapper.mapDtoToDao(car).get());

        return ResponseEntity.badRequest().build();
    }



    /* DELETE */

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CarDto car)
    {
        if(Optional.ofNullable(car).isPresent())
                return service.delete(mapper.mapDtoToDao(car).get());

        return ResponseEntity.badRequest().build();
    }


}
