package com.example.App.controllers;

import com.example.App.Components.MapperServiceClient;
import com.example.App.DTO.ClientDto;
import com.example.App.Services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
@RestController
public class ClientController {

    @Autowired private MapperServiceClient mapper;

    @Autowired private ClientServiceImpl service;


    /* GET */

    @GetMapping("{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable("id") Integer id)
    {
        return service.findById(id)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    List<ClientDto> getAll(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "dni", required = false) String dni)
    {
        List<ClientDto> list;

        if (Optional.ofNullable(name).isPresent() && Optional.ofNullable(dni).isPresent())
            list = (mapper.mapListDaoToDto(service.findAllByNameContainingAndDni(name, dni)));
        else
            if(Optional.ofNullable(name).isPresent())
                list = mapper.mapListDaoToDto(service.findAllByNameContaining(name));
            else if(Optional.ofNullable(dni).isPresent())
                list = mapper.mapListDaoToDto(service.findAllByDni(dni));
                else list = mapper.mapListDaoToDto(service.findAll());

        return list;
    }

    /* POST */

    @PostMapping
    public ResponseEntity<ClientDto> post(@RequestBody ClientDto client)
    {
        return Optional.ofNullable(client)
                .flatMap(mapper::mapDtoToDao)
                .flatMap(service::create)
                .flatMap(mapper::mapDaoToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    /* PUT */

    @PutMapping
    public ResponseEntity<?> put(@RequestBody ClientDto client)
    {
        if(Optional.ofNullable(client).isPresent())
            if(mapper.mapDtoToDao(client).isPresent())
                return service.update(mapper.mapDtoToDao(client).get());

        return ResponseEntity.badRequest().build();

    }

    /* DELETE */

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody ClientDto client)
    {
        if(Optional.ofNullable(client).isPresent())
            if(mapper.mapDtoToDao(client).isPresent())
                return service.delete(mapper.mapDtoToDao(client).get());

        return ResponseEntity.badRequest().build();
    }

}
