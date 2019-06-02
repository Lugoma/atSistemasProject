package com.example.App.Components;

import com.example.App.DTO.CarDto;
import com.example.App.DTO.ClientDto;
import com.example.App.DTO.RentalDto;
import com.example.App.model.Car;
import com.example.App.model.Client;
import com.example.App.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MapperServiceRental implements MapperService<RentalDto, Rental> {

    @Autowired private DateStringMapper dateMapper;

    @Autowired private MapperServiceCar carMapper;

    @Autowired private MapperServiceClient clientMapper;

    @Override
    public Optional<Rental> mapDtoToDao(RentalDto dto) {
        if(Optional.ofNullable(dto).isPresent()
                && Optional.ofNullable(dto.getCarDto()).isPresent()
                && Optional.ofNullable(dto.getClientDto()).isPresent())
        {
            Car car = carMapper.mapDtoToDao(dto.getCarDto()).get();
            Client client = clientMapper.mapDtoToDao(dto.getClientDto()).get();
            LocalDate startDate = dateMapper.map(dto.getStartDate());
            LocalDate endDate = dateMapper.map(dto.getEndDate());

            return Optional.of(new Rental(dto.getId(), startDate, endDate, dto.getPrice(), client, car));

        }
        return Optional.empty();
    }

    @Override
    public Optional<RentalDto> mapDaoToDto(Rental dao) {
        if(Optional.ofNullable(dao).isPresent()
                && Optional.ofNullable(dao.getCar()).isPresent()
                && Optional.ofNullable(dao.getClient()).isPresent())
        {
            CarDto car = carMapper.mapDaoToDto(dao.getCar()).get();
            ClientDto client = clientMapper.mapDaoToDto(dao.getClient()).get();


            return Optional.of(new RentalDto(dao.getIdRent(), car, client, dao.getPrice(),
                            dao.getStartDate().toString(), dao.getEndDate().toString()));

        }
        return Optional.empty();
    }

    @Override
    public List<Rental> mapListDtoToDao(List<RentalDto> dtoList) {
        List<Rental> list = new ArrayList<>();

        for (RentalDto dto : dtoList)
            if(mapDtoToDao(dto).isPresent())
                list.add(mapDtoToDao(dto).get());

        return list;
    }

    @Override
    public List<RentalDto> mapListDaoToDto(List<Rental> daoList) {
        List<RentalDto> list = new ArrayList<>();

        for (Rental dao : daoList)
            if(mapDaoToDto(dao).isPresent())
                list.add(mapDaoToDto(dao).get());

        return list;
    }
}
