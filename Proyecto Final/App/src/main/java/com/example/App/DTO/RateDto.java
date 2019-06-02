package com.example.App.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    @NotNull(message = "El campo id es nulo")
    private Integer id;
    @NotNull(message = "El campo price es nulo")
    private Double price;

    @NotNull(message = "El campo startDate es nulo")
    private String startDate;

    @NotNull(message = "El campo endDate es nulo")
    private String endDate;
}
