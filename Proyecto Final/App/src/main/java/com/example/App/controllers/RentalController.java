package com.example.App.controllers;

import com.example.App.Components.DateStringMapper;
import com.example.App.Components.MapperServiceRental;
import com.example.App.DTO.RentalDto;
import com.example.App.Services.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rental")
@RestController
public class RentalController {

    @Autowired private MapperServiceRental mapper;

    @Autowired private RentalServiceImpl service;

    @Autowired private DateStringMapper dateMapper;


    /* GET */

    @GetMapping("{id}")
    public ResponseEntity<RentalDto> getById(@PathVariable("id") Integer id)
    {
        return service.findById(id)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<RentalDto> getAll(@RequestParam(value = "price", required = false) Double price)
    {
        if (Optional.ofNullable(price).isPresent())
            return mapper.mapListDaoToDto(service.findAllByPrice(price));

        return mapper.mapListDaoToDto(service.findAll());
    }


    @GetMapping("/client/{id}")
    public List<RentalDto> getByClient(@PathVariable("id") Integer id)
    {
        return mapper.mapListDaoToDto(service.findAllByClient(id));
    }

    @GetMapping("/car/{id}")
    public List<RentalDto> getByCar(@PathVariable("id") Integer id)
    {
        return mapper.mapListDaoToDto(service.findAllByCar(id));
    }

    /* POST */

    @PostMapping
    public ResponseEntity<RentalDto> post(@RequestBody RentalDto rental)
    {
        return Optional.ofNullable(rental)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /* PUT */
    @PutMapping
    public  ResponseEntity<?> put(@RequestBody RentalDto rental)
    {
        if(Optional.ofNullable(rental).isPresent())
            if(mapper.mapDtoToDao(rental).isPresent())
                return service.update(mapper.mapDtoToDao(rental).get());

        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody RentalDto rental)
    {
        if(Optional.ofNullable(rental).isPresent())
            if(mapper.mapDtoToDao(rental).isPresent())
                return service.delete(mapper.mapDtoToDao(rental).get());

        return ResponseEntity.badRequest().build();
    }

}
