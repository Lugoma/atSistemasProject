package com.example.App.Components;

import com.example.App.DTO.RateDto;
import com.example.App.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MapperServiceRate implements MapperService<RateDto, Rate> {

    @Autowired private DateStringMapper dateMaper;

    @Override
    public Optional<Rate> mapDtoToDao(RateDto dto) {
        if (Optional.ofNullable(dto).isPresent() && !dto.getStartDate().isEmpty() && !dto.getEndDate().isEmpty())
        {
            LocalDate startDate = dateMaper.map(dto.getStartDate());
            LocalDate endDate = dateMaper.map(dto.getEndDate());

            return Optional.of(new Rate(dto.getId(), startDate, endDate, dto.getPrice()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RateDto> mapDaoToDto(Rate dao) {
        if(Optional.ofNullable(dao).isPresent())
            return Optional.of(new RateDto(dao.getIdRate(), dao.getPrice(), dao.getStartDate().toString(), dao.getEndDate().toString()));
        return Optional.empty();
    }

    @Override
    public List<Rate> mapListDtoToDao(List<RateDto> listDto) {
        List<Rate> list = new ArrayList<>();
        for (RateDto rateDto : listDto)
            if(mapDtoToDao(rateDto).isPresent())
                list.add(mapDtoToDao(rateDto).get());

        return list;
    }

    @Override
    public List<RateDto> mapListDaoToDto(List<Rate> listDao) {
        List<RateDto> list = new ArrayList<>();
        for (Rate rate : listDao)
            if(mapDaoToDto(rate).isPresent())
                list.add(mapDaoToDto(rate).get());

        return list;
    }
}
