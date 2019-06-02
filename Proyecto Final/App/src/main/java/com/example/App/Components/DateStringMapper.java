package com.example.App.Components;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class DateStringMapper {

    public LocalDate map(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]");

        return LocalDate.from(formatter.parse(date));
    }
}
