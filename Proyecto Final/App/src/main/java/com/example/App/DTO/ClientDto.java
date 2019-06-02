package com.example.App.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    @NotNull(message = "El campo id es nulo")
    private Integer id;

    @NotNull(message = "El campo dni es nulo")
    private String dni;

    @NotNull(message = "El campo name es nulo")
    private String name;
}
