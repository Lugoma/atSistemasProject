package com.example.App.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotNull(message = "El campo id es nulo")
    private Integer id;

    @NotNull(message = "El campo carPlate es nulo")
    private String carPlate;

    @NotNull(message = "El campo resgistrationYear es nulo")
    private String registrationYear;
}
