package com.example.App.Components;

import com.example.App.DTO.CarDto;
import com.example.App.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MapperServiceCar implements MapperService<CarDto, Car> {

    @Autowired private DateStringMapper mapper;

    @Override
    public Optional<CarDto> mapDaoToDto(Car car) {
        if(Optional.ofNullable(car).isPresent())
            return Optional.of(new CarDto(car.getIdCar(), car.getCarPlate(), car.getRegistrationYear().toString()));

        return Optional.empty();
    }

    @Override
    public Optional<Car> mapDtoToDao(CarDto carDto) {
        if(Optional.ofNullable(carDto).isPresent() && !carDto.getRegistrationYear().isEmpty())
        {
            LocalDate date = mapper.map(carDto.getRegistrationYear());

            return Optional.of(new Car(carDto.getId(), carDto.getCarPlate(), date));
        }
        return Optional.empty();
    }

    @Override
    public List<CarDto> mapListDaoToDto(List<Car> listDao) {
        List<CarDto> list = new ArrayList<>();
        for(Car car : listDao)
            if(mapDaoToDto(car).isPresent())
                list.add(mapDaoToDto(car).get());

        return list;
    }

    @Override
    public List<Car> mapListDtoToDao(List<CarDto> listDto) {
        List<Car> list = new ArrayList<>();
        for(CarDto carDto : listDto)
            if(mapDtoToDao(carDto).isPresent())
                list.add(mapDtoToDao(carDto).get());

        return list;
    }
}
